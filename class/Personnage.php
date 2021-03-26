<?php

class Personnage{
    
    private $_id;
    private $_nom;
    private $_vie;
    private $_degat;

    private $map;

    private $_bdd;

    public function __construct($bdd){
        $this->_bdd = $bdd;
    }

    public function setPersonnage($id,$nom,$vie,$degat){
        $this->_id = $id;
        $this->_nom = $nom;
        $this->_vie = $vie;
        $this->_degat = $degat;
    }

    public function getNom(){
        return $this->_nom;
    }
    
    public function getId(){
        return $this->_id;
    }

    public function getMap(){
        return $this->map;
    }

    //permet de changer la position du joueur sur la carte
    public function changeMap($NewMap){
        $this->map = $NewMap;
        //on mémorise çà en base
        $sql = "UPDATE `Personnage` SET `idMap`='".$NewMap->getId()."' WHERE `id`='".$this->_id."'";
        $this->_bdd->query($sql);
        
    }

    public function setPersonnageById($id){
        $Result = $this->_bdd->query("SELECT * FROM `Personnage` WHERE `id`='".$id."' ");
        if($tab = $Result->fetch()){ 

            $this->setPersonnage($tab["id"],$tab["nom"],$tab["vie"],$tab["degat"]);
            //recherche de sa position
            $map = new map($this->_bdd);
            $map->setMapByID($tab["idMap"]);
            $this->map = $map;
            
        }
    }

    //Retourne une liste HTML de tous les personnages
    //et permet d'attribuer un perso à un user
    // retour un objet personnage
    public function getChoixPersonnage(){
        $Result = $this->_bdd->query("SELECT * FROM `Personnage` ");
        ?>
        <form action="" method="post" onchange="this.submit()">
            <select name="idPersonnage" id="idPersonnage">
            <option value=""> Choisi un perso</option>;
                <?php while($tab=$Result->fetch()){
                    echo '<option value="'.$tab["id"].'"> '.$tab["nom"].'</option>';

                }
                ?>
            </select>
        </form>
        <?php
        if (isset($_POST["idPersonnage"])){
            $this->setPersonnageById($_POST["idPersonnage"]);
        }

        return $this;
    }

}

?>