<?php
// TODO MOB ET PERSONNAGE ON TROP DE SIMILITUDE 
//IL FAUT REFACTORISER AVEC DE LhERITAGE

class Personnage{
    
    private $_id;
    private $_nom;
    private $_vie;
    private $_vieMax;
    private $_degat;
    private $_imageLien;

    private $map;

    private $_bdd;

    private $sacItems=array();

    public function __construct($bdd){
        $this->_bdd = $bdd;
    }

    public function getVie(){
        return $this->_vie ;
    }

    public function getBardeVie(){
        $pourcentage = round(100*$this->_vie/$this->_vieMax);
        ?>
        <div class="PersoPrincipalBarreVie">
            
            <div class="attaque" id="attaquePersoValeur<?php echo $this->_id ;?>"> <?php echo $this->_degat ;?>  </div> 
            <div class="barreDeVie" id="viePerso<?php echo $this->_id ;?>">
                
                <div class="vie" id="viePersoValeur<?php echo $this->_id ;?>" style="width: <?php echo $pourcentage?>%;">
                ♥️<?php echo $this->_vie ;?>
                </div>
            </div>
        </div>
        <?php
    }

    public function getVieMax(){
        return $this->_vieMax ;
    }

    public function getAttaque(){
        return $this->_degat;
    }

    public function SubitDegatByPersonnage($Personnage){
        $this->_vie = $this->_vie - $Personnage->getAttaque();
        if($this->_vie<0){
            $this->_vie =0;
            //retour en zone 0,0
        }
        $req  = "UPDATE `Personnage` SET `vie`='".$this->_vie ."' WHERE `id` = '".$this->_id ."'";
        $Result = $this->_bdd->query($req);
        return $this->_vie;
    }

    public function getAllMyMobIdByMap($map){
        $listMob=array();
        $req="SELECT `id` FROM `Mob` WHERE `idPersoProprio` = '".$this->_id."' AND `idMap` = '".$map->getId()."' )";
        $Result = $this->_bdd->query($req);
        while($tab=$Result->fetch()){
            array_push($listMob,$tab);
        }
        return $listMob;
    }

    public function SubitDegatByMob($Mob){

        $MobDegatAttaqueEnvoyer=$Mob->getAttaque();
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
        $req  = "UPDATE `Personnage` SET `vie`='".$this->_vie ."' WHERE `id` = '".$this->_id ."'";
        $Result = $this->_bdd->query($req);


        //update AttaquePersoMob pour mettre a jour combien le perso a pris de degat 
        $req="UPDATE `AttaquePersoMob` SET 
        `DegatsDonnes`=".$tabAttaque['DegatsDonnes']."
         WHERE idMob = '".$Mob->getId()."' AND idPersonnage ='".$this->_id."' ";
        $Result = $this->_bdd->query($req);

        return $this->_vie;
    }

    public function setPersonnage($id,$nom,$vie,$degat,$vieMax,$image){
        $this->_id = $id;
        $this->_nom = $nom;
        $this->_vie = $vie;
        $this->_vieMax = $vieMax;
        $this->_degat = $degat;
        $this->_imageLien = $image;

         //select les items déjà présent
         $req  = "SELECT idItem FROM `PersoSacItems` WHERE idPersonnage='".$id."'";
         $Result = $this->_bdd->query($req);
         while($tab=$Result->fetch()){
             array_push($this->sacItems,$tab[0]);
         }
    }

    public function getNom(){
        return $this->_nom;
    }

    //met a jour la vie de depart et replace le joueur
    public function resurection(){
        $vieMax = intdiv ($this->_vieMax,2);
        $attaque = intdiv ($this->_vieMax,2);
        if($vieMax<10){$vieMax=10;}
        $req  = "UPDATE `Personnage` SET `degat`='".$attaque."',`vieMax`='".$vieMax."',`vie`='".$vieMax."' WHERE `id` = '".$this->_id ."'";
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
        return  $valeur;
    }

    //retourne toute la mécanique d'affichage d'un Personnage
    public function renderHTML(){
        $pourcentage = round(100*$this->_vie/$this->_vieMax);
        ?>
        <div class="perso">
            <div>
            <?php echo $this->_nom ?>( <?php echo $this->getValeur() ?> NFT)
            </div>
            <div><img class="Personnage" src="<?php echo $this->_imageLien;?>">
            </div>
            <div class="attaque" id="attaquePersoValeur<?php echo $this->_id ;?>"> <?php echo $this->_degat ;?>  </div> 
            <div class="barreDeVie" id="viePerso<?php echo $this->_id ;?>">
                
                 <div class="vie" id="viePersoValeur<?php echo $this->_id ;?>" style="width: <?php echo $pourcentage?>%;">♥️<?php echo $this->_vie ;?></div>
            </div>
        </div>

        <?php
    }
    
    public function getId(){
        return $this->_id;
    }

    public function getMap(){
        return $this->map;
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

    public function lvlupAttaque($attaque){
        $this->_degat += $attaque;
        $sql = "UPDATE `Personnage` SET `degat`='".$this->_degat."' WHERE `id`='".$this->_id."'";
        $this->_bdd->query($sql);
    }
    public function lvlupVie($viemore){
        $this->_vie += $viemore;
        $sql = "UPDATE `Personnage` SET `vie`='".$this->_vie."' WHERE `id`='".$this->_id."'";
        $this->_bdd->query($sql);

    }
    public function lvlupVieMax($viemore){
        $this->_vieMax += $viemore;
        $sql = "UPDATE `Personnage` SET `vieMax`='".$this->_vieMax."' WHERE `id`='".$this->_id."'";
        $this->_bdd->query($sql);

    }


    //permet de changer la position du joueur sur la carte
    public function changeMap($NewMap){
        $this->map = $NewMap;
        //on mémorise çà en base
        $sql = "UPDATE `Personnage` SET `idMap`='".$NewMap->getId()."' WHERE `id`='".$this->_id."'";
        $this->_bdd->query($sql);
        
    }

    public function removeItemByID($id){
        unset($this->sacItems[array_search($id, $this->sacItems)]);
        $req="DELETE FROM `PersoSacItems` WHERE idPersonnage='".$this->getId()."' AND idItem='".$id."'";
        $this->_bdd->query($req);
        $req="DELETE FROM `Item` WHERE id='".$id."'";
        $this->_bdd->query($req);
    }

    public function setPersonnageById($id){
        $Result = $this->_bdd->query("SELECT * FROM `Personnage` WHERE `id`='".$id."' ");
        if($tab = $Result->fetch()){ 

            $this->setPersonnage($tab["id"],$tab["nom"],$tab["vie"],$tab["degat"],$tab["vieMax"],$tab["lienImage"]);
            
            //recherche de sa position
            $map = new map($this->_bdd);
            $map->setMapByID($tab["idMap"]);
            $this->map = $map;
            
        }
    }

    public function setPersonnageByIdWithoutMap($id){
        $Result = $this->_bdd->query("SELECT * FROM `Personnage` WHERE `id`='".$id."' ");
        if($tab = $Result->fetch()){ 

            $this->setPersonnage($tab["id"],$tab["nom"],$tab["vie"],$tab["degat"],$tab["vieMax"],$tab["lienImage"]);
            
        }
    }

    //ajoute un lien entre item et la personnage en bdd 
    //et accroche l'item dans la collection itemID dans le sac du perso
    public function addItem($newItem){
        array_push($this->sacItems,$newItem->getId());
        $req="INSERT INTO `PersoSacItems`(`idPersonnage`, `idItem`) VALUES ('".$this->getId()."','".$newItem->getId()."')";
        $this->_bdd->query($req);
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
            <div>Creer un Perso ou choisie en un</div>
           <input type="text" name="NomPersonnage" required>
           <input type="submit" value="Creer" name="createPerso">
           <input type="hidden" name="image" value="<?php echo $imageUrl;?>">
        </form>
        </div>
        <?php
        if (isset($_POST["createPerso"])){
            $newperso = new Personnage($this->_bdd);
            $this->_nom=htmlentities($_POST['NomPersonnage'], ENT_QUOTES);
            $this->_imageLien=$_POST['image'];
            $req="INSERT INTO `Personnage`(`nom`, `vie`, `degat`, `idMap`,`vieMax`,`lienImage`,`idUser`) VALUES ('".$this->_nom."',10,10,0,10,'".$this->_imageLien."','".$idUser."')";
            $this->_bdd->beginTransaction();
            $Result = $this->_bdd->query($req);
            $lastID = $this->_bdd->lastInsertId();
            if($lastID){ 
                $newperso->setPersonnageById($lastID);
                $this->_bdd->commit();
                return $newperso;
            }else{
                $this->_bdd->rollback();
                return null;
            }
        }

        return null;
    }


     //Retourne une liste HTML de tous les personnages
    //et permet d'attribuer un perso à un user
    // retour un objet personnage
    public function getChoixPersonnage($idUser){
        if (isset($_POST["idPersonnage"])){
            $this->setPersonnageById($_POST["idPersonnage"]);
            if($this->_vie==0){
                $this->resurection();
            }
            //si le personnage est mort on le place ne origine 0,0
        }

        $Result = $this->_bdd->query("SELECT * FROM `Personnage` where idUser='".$idUser."' ");
        ?>
        <form action="" method="post" onchange="this.submit()">
            <select name="idPersonnage" id="idPersonnage">
            <option value=""> Choisi un perso</option>
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

    public function generateImage(){

        switch (rand(0,3)) {
            case 0:
                $topic='league+of+legend+fan+art';
                break;
            case 1:
                $topic='manga+fan+art';
                break;
            case 2:
                $topic='marvel+fan+art';
                break;
            case 3:
                $topic='comics+fan+art';
                break;
            default:
                $topic='fantasy+fan+art';
                break;
        }

        $ofs=mt_rand(0, 100);
        $geturl='http://www.google.ca/images?q=' . $topic . '&start=' . $ofs . '&gbv=1';
        $data=file_get_contents($geturl);
        

        //partialString1 is bigger link.. in it will be a scr for the beginning of the url
        $f1='<div class="lIMUZd"><div><table class="TxbwNb"><tr><td><a href="/url?q=';
        $pos1=strpos($data, $f1)+strlen($f1);
        $partialString1 = substr($data, $pos1);
        
        //partialString 2 starts with the URL
        $f2='src="';
        $pos2=strpos($partialString1, $f2)+strlen($f2);
        $partialString2 = substr($partialString1, $pos2, 400);
        
        //PartialString3 ends the url when it sees the "&amp;"
        $f3='&amp;';
        $urlLength=strpos($partialString2, $f3);
        $partialString3 = substr($partialString2, 0,  $urlLength);
        
        return $partialString3;
    }
}

?>