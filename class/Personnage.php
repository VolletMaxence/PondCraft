<?php
// TODO MOB ET PERSONNAGE ON TROP DE SIMILITUDE 
//IL FAUT REFACTORISER AVEC DE LhERITAGE

class Personnage extends Entite{
    
    private $_xp;

    private $sacItems=array();
 

    public function __construct($bdd){

        Parent::__construct($bdd);
    }

    public function getXp(){
        return $this->_xp;
    }
  

    public function SubitDegatByPersonnage($Personnage){
        $degat = $Personnage->getAttaque();
        //on réduit les déga avec armure si possible
        $degat-=($degat*$this->getDefense())/100;
        $degat = round($degat);
        if($degat<0){
            $degat = 0;
        }

        $this->_vie = $this->_vie - $degat;
        if($this->_vie<0){
            $this->_vie =0;
            //retour en zone 0,0
        }
        $req  = "UPDATE `Entite` SET `vie`='".$this->_vie ."' WHERE `id` = '".$this->_id ."'";
        $Result = $this->_bdd->query($req);

       

        return $this->_vie;
    }


    //todo peut etre factoriser dans la class mère Entite
    public function SubitDegatByMob($Mob){

        $MobDegatAttaqueEnvoyer=$Mob->getAttaque();

        //on réduit les déga avec armure si possible
        $MobDegatAttaqueEnvoyer-=($MobDegatAttaqueEnvoyer*$this->getDefense())/100;
        $MobDegatAttaqueEnvoyer = round($MobDegatAttaqueEnvoyer);
        if($MobDegatAttaqueEnvoyer<0){
            $MobDegatAttaqueEnvoyer = 0;
        }

        $vieAvantAttaque = $this->_vie;

        //on va rechercher l'historique
        $req  = "SELECT * FROM `AttaquePersoMob` where idMob = '".$Mob->getId()."' and idPersonnage = '".$this->_id."'" ;
        $Result = $this->_bdd->query($req);
        $tabAttaque['nbCoup']=0;
        $tabAttaque['DegatsDonnes']=$MobDegatAttaqueEnvoyer;
        if($tab=$Result->fetch()){
            $tabAttaque = $tab;
            $tabAttaque['DegatsDonnes']+=$MobDegatAttaqueEnvoyer;
            $tabAttaque['nbCoup']++;

        }else{
            //insertion d'une nouvelle attaque
            $req="INSERT INTO `AttaquePersoMob`(`idMob`, `idPersonnage`, `nbCoup`, `coupFatal`, `DegatsDonnes`, `DegatsReçus`) 
            VALUES (
                '".$Mob->getId()."','".$this->_id."',0,0,".$tabAttaque['DegatsReçus'].",0
            )";
            $Result = $this->_bdd->query($req);
        }


        $this->_vie = $this->_vie - $MobDegatAttaqueEnvoyer;
        if($this->_vie<0){
            $this->_vie =0;
            //on ne peut pas donner plus de degat que la vie d'un perso
            $tabAttaque['DegatsDonnes'] = $vieAvantAttaque;
            //retour en zone 0,0
        }
        $req  = "UPDATE `Entite` SET `vie`='".$this->_vie ."' WHERE `id` = '".$this->_id ."'";
        $Result = $this->_bdd->query($req);

        //update AttaquePersoMob pour mettre a jour combien le perso a pris de degat 
        $req="UPDATE `AttaquePersoMob` SET 
        `DegatsDonnes`=".$tabAttaque['DegatsDonnes']."
         WHERE idMob = '".$Mob->getId()."' AND idPersonnage ='".$this->_id."' ";
        $Result = $this->_bdd->query($req);

        return $this->_vie;
    }

    public function setPersonnage($xp){
        //un personnage n'a pas de propriete en plus pour le moment
        $this->_xp = $xp;
    }

    //retourne la nouvelle xp 
    public function addXP($value){
        $this->_xp += $value ;
        
        $req  = "UPDATE `Personnage` SET `xp`='".$this->_xp ."' WHERE `id` = '".$this->_id ."'";
        $Result = $this->_bdd->query($req);

        //passage des Lvl suis une loi de racine carre
        //* le double etole ** c'est elevé à la puissance */
        $lvl = ceil(($this->_xp/2000)**(0.7));

        if($lvl >$this->_lvl ){
            $this->_lvl = $lvl;
            $req  = "UPDATE `Entite` SET `lvl`='".$this->_lvl."' WHERE `id` = '".$this->_id ."'";
            $Result = $this->_bdd->query($req);
        }

        return $this->_xp;
    }
  
    //met a jour la vie de depart et replace le joueur
    public function resurection(){
        $vieMax = intdiv ($this->_vieMax,2);
        $attaque = intdiv ($this->_vieMax,2);
        if($vieMax<10){$vieMax=10;}
        $req  = "UPDATE `Entite` SET `degat`='".$attaque."',`vieMax`='".$vieMax."',`vie`='".$vieMax."' WHERE `id` = '".$this->_id ."'";
        $Result = $this->_bdd->query($req);
        $this->_vie=$vieMax;
        $this->_vieMax=$vieMax;
        $this->_degat=$attaque;
        $maporigine = new Map($this->_bdd);
        $maporigine->setMapByID(0);
        $this->changeMap($maporigine);
    }

    //retourne un entier de toutes ses valeurs
    public function getValeur(){
        $valeur = 0;
        foreach ($this->getItems() as $value) {
            $valeur+=$value->getValeur();
        }
        foreach ($this->getEquipements() as $value) {
            $valeur+=$value->getValeur();
        }
        return  $valeur;
    }

    //retourne toute la mécanique d'affichage d'un Personnage
    public function renderHTML(){
       
        ?>
        <div class="perso">
            <div class="persoXP"><?php echo $this->_xp?>(xp)</div>
            <?php
                Parent::renderHTML();
            ?>
        </div>

        <?php
    }

    public function getItems(){
        $lists=array();
        foreach ($this->sacItems  as $ItemId) {
            $newItem = new Item($this->_bdd);
            $newItem->setItemByID($ItemId);
            array_push($lists,$newItem);
        }
        return $lists;
    }


  

   //Retourne un formulaire HTML pourcreer un personnage
    //et permet d'attribuer automatiquement à user
    // retour un objet personnage
    public function CreatNewPersonnage($idUser){
        ?>
        <div class = "formCreatio">
        <?php $imageUrl = $this->generateImage(); ?>

        <form action="" method="post" onclick="this.submit()">
            <img src="<?php echo $imageUrl;?>" width="200px" >
        </form>

        <form action="" method="post">
            <div>Créez un personnage ou choisissez-en un :</div>
            <input type="text" name="NomPersonnage" required>
            <input type="submit" value="Creer" name="createPerso">
            <input type="hidden" name="image" value="<?php echo $imageUrl;?>">
        </form>
        </div>
        <?php
        if (isset($_POST["createPerso"])){

            $newperso = new Personnage($this->_bdd);
            $newperso = $newperso->CreateEntite($_POST['NomPersonnage'], 100, 10, 0,100,$_POST['image'],$idUser,1,1);

            if($newperso->getId()){ 
                $req="INSERT INTO `Personnage`(`id`) VALUES ('".$newperso->getId()."')";
                $Result = $this->_bdd->query($req);
                $newperso->setEntiteById($newperso->getId());
                return $newperso;
            }else{
                $this->_bdd->rollback();
                return null;
            }
        }

        return null;
    }

    public function setPersonnageByIdWithoutMap($id){
        Parent::setEntiteByIdWithoutMap($id);
    }


    public function setPersonnageById($id){
        Parent::setEntiteById($id);

        //select les info personnage
        $req  = "SELECT * FROM `Personnage` WHERE id='".$id."'";
        $Result = $this->_bdd->query($req);
        if($tab=$Result->fetch()){
            $this->_xp  = $tab['xp'];
        }else{
            $req  = "INSERT  INTO `Personnage` (id,xp) VALUE ('".$id."','10')";
            $Result = $this->_bdd->query($req);
        }

        //select les items déjà présent
        $req  = "SELECT idItem FROM `PersoSacItems` WHERE idPersonnage='".$id."'";
        $Result = $this->_bdd->query($req);
        while($tab=$Result->fetch()){
            array_push($this->sacItems,$tab[0]);
        }
    }


    public function removeItemByID($id){
        unset($this->sacItems[array_search($id, $this->sacItems)]);
        $req="DELETE FROM `PersoSacItems` WHERE idPersonnage='".$this->getId()."' AND idItem='".$id."'";
        $this->_bdd->query($req);
        $req="DELETE FROM `Item` WHERE id='".$id."'";
        $this->_bdd->query($req);
    }

    
    
   
 

   
    //ajoute un lien entre item et la personnage en bdd 
    //et accroche l'item dans la collection itemID dans le sac du perso
    public function addItem($newItem){
        array_push($this->sacItems,$newItem->getId());
        $req="INSERT INTO `PersoSacItems`(`idPersonnage`, `idItem`) VALUES ('".$this->getId()."','".$newItem->getId()."')";
        $this->_bdd->query($req);
    }

    

    //Retourne une liste HTML de tous les personnages
    //et permet d'attribuer un perso à un user
    // retour un objet personnage
    public function getChoixPersonnage($User){
        if (isset($_POST["idPersonnage"])){
            $this->setPersonnageById($_POST["idPersonnage"]);
            $User->setPersonnage($this);
            if($this->_vie==0){
                $this->resurection();
            }
            //si le personnage est mort on le place ne origine 0,0
        }
        $Result = $this->_bdd->query("SELECT * FROM `Entite` where idUser='".$User->getId()."' AND type=1 ");
        ?>
        <form action="" method="post" onchange="this.submit()">
            <select name="idPersonnage" id="idPersonnage">
            <option value="">Choisir un personnage</option>
                <?php while($tab=$Result->fetch()){
                    ($tab['id']==$this->_id)?$selected='selected':$selected='';
                    echo '<option value="'.$tab["id"].'" '.$selected.'> '.$tab["nom"].'</option>';
                }
                ?>
            </select>
        </form>
        <?php
        return $this;
    }

}
?>