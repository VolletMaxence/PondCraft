<?php

class User{

    private $_id;
    private $_login;
    private $_mdp;
    private $_prenom;

    private $_bdd;


    public function __construct($bdd){
        $this->_bdd = $bdd;
    }

    public function setUser($id,$login,$mdp,$prenom){
        $this->_id = $id;
        $this->_login = $login;
        $this->_mdp = $mdp;
        $this->_prenom = $prenom;
    }

    public function setUserById($id){
        $Result = $this->_bdd->query("SELECT * FROM `User` WHERE `id`='".$id."' ");
        if($tab = $Result->fetch()){ 

            $this->setUser($tab["id"],$tab["login"],$tab["mdp"],$tab["prenom"]);
        }


    }

    public function getPrenom(){
        return $this->_prenom;
    }

    public function ConnectToi(){

       //traitement du formulaire
    $access = false;
    if( isset($_POST["login"]) && isset($_POST["password"])){
        //verif mdp en BDD

        $Result = $this->_bdd->query("SELECT * FROM `User` WHERE `login`='".$_POST['login']."' AND `mdp` = '".$_POST['password']."'");
        if($tab = $Result->fetch()){ 

            $this->setUser($tab["id"],$tab["login"],$tab["mdp"],$tab["prenom"]);

             //si mdp = ok
            $access = true;
            $_SESSION["idUser"]= $tab["id"];
            $_SESSION["Connected"]=true;
            $afficheForm = false;
            //si on est co on affiche le formulaire de deco
            $this->DeconnectToi();
        }else{
            $afficheForm = true;
        }

                    

    }else{
        $afficheForm = true;
    }
    
    if($afficheForm){
    ?>
        <form action="" method="post" >
            <div>
                <label for="login">Enter your login: </label>
                <input type="text" name="login" id="login" required value="Rapidecho">
            </div>
            <div >
                <label for="password">Enter your pass: </label>
                <input type="password" name="password" id="password" required value="Julien1234">
            </div>
            <div >
                <input type="submit" value="Go!" >
            </div>
        </form>

    <?php
    }

    return $access;
    }

    public function DeconnectToi(){

         //traitement du formulaire
    $afficheForm = true;
    $access = true;
    if( isset($_POST["logout"]) && isset($_POST["logout"])){
        //si on se deco on raffiche le formulaire de co
        $_SESSION["Connected"]=false;
        session_unset();
        session_destroy();
        $this->ConnectToi();
        $afficheForm = false;
        $access = false;
    }else{
        $afficheForm = true;
    }

    if($afficheForm){
    ?>
        <form action="" method="post" >
            <div >
                <input type="submit" value="Deco!" name="logout">
            </div>
        </form>

    <?php
    
    }

    return $access;
    }

}

?>