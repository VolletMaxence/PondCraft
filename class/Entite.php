<?php
// TODO MOB ET PERSONNAGE ON TROP DE SIMILITUDE 
//IL FAUT REFACTORISER AVEC DE LhERITAGE

class Entite {
    
    protected $_id;
    protected $_nom;
    protected $_vie;
    protected $_vieMax;
    protected $_degat;
    protected $_imageLien;
    protected $_lvl;
    private $sacEquipements=array();

    protected $_type; //1 = hero 2= mob

    protected $map;

    protected $_bdd;

    //private $sacItems=array();

    public function __construct($bdd){
        $this->_bdd = $bdd;
    }



    public function removeEquipementByID($id){
        unset($this->sacEquipements[array_search($id, $this->sacEquipements)]);
        $req="DELETE FROM `EntiteEquipement` WHERE idEntite='".$this->getId()."' AND idEquipement='".$id."'";
        $this->_bdd->query($req);

        //todo retirer un equipement ne doit pas etre une suppression
        $req="DELETE FROM `Equipement` WHERE id='".$id."'";
        $this->_bdd->query($req);
    }

   
    //ajoute un lien entre item et la personnage en bdd 
    //et accroche l'item dans la collection itemID dans le sac du perso
    public function addEquipement($newEquipement){
        array_push($this->sacEquipements,$newEquipement->getId());
        $req="INSERT INTO `EntiteEquipement`(`idEntite`, `idEquipement`) VALUES ('".$this->getId()."','".$newEquipement->getId()."')";
        $this->_bdd->query($req);
    }

    public function getVie(){
        return $this->_vie ;
    }

    public function getLvl(){
        return $this->_lvl ;
    }

    public function getEquipements(){
        $lists=array();
        foreach ($this->sacEquipements  as $EquipementId) {
            $newEquipement = new Equipement($this->_bdd);
            $newEquipement->setEquipementByID($EquipementId);
            array_push($lists,$newEquipement);
        }
        return $lists;
    }

    public function getBardeVie(){
        $pourcentage = round(100*$this->_vie/$this->_vieMax);
        ?>
        <div class="EntitePrincipalBarreVie">

            <div class="attaque" id="attaqueEntiteValeur<?php echo $this->_id ;?>"> <?php echo $this->_degat ;?>  </div> 
            <div class="barreDeVie" id="vieEntite<?php echo $this->_id ;?>">

                <div class="vie" id="vieEntiteValeur<?php echo $this->_id ;?>" style="width: <?php echo $pourcentage?>%;">
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

    //il n'est possible de booster la vie au dela de vie max
    public function SoinPourcentage($pourcentage){
        $valeur = round(($this->_vieMax*$pourcentage)/100);
        $this->_vie =  $valeur+ $this->_vie;
        if ($this->_vie>$this->_vieMax){
            $this->_vie = $this->_vieMax;
        }
        $req  = "UPDATE `Entite` SET `vie`='".$this->_vie ."' WHERE `id` = '".$this->_id ."'";
        $Result = $this->_bdd->query($req);
        return $valeur;
    }

    public function SubitDegatByEntite($Entite){
        $this->_vie = $this->_vie - $Entite->getAttaque();
        if($this->_vie<0){
            $this->_vie =0;
            //retour en zone 0,0
        }
        $req  = "UPDATE `Entite` SET `vie`='".$this->_vie ."' WHERE `id` = '".$this->_id ."'";
        $Result = $this->_bdd->query($req);
        return $this->_vie;
    }

    
    public function getAllMyMobIdByMap($map){
        $listMob=array();
        $req="SELECT `id` FROM `Entite` WHERE `idUser` = '".$this->_id."' AND `idMap` = '".$map->getId()."' )";
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
        $req  = "SELECT * FROM `AttaqueEntiteMob` where idMob = '".$Mob->getId()."' and idEntite = '".$this->_id."'" ;
        $Result = $this->_bdd->query($req);
        $tabAttaque['nbCoup']=0;
        $tabAttaque['DegatsDonnes']=$MobDegatAttaqueEnvoyer;
        if($tab=$Result->fetch()){
            $tabAttaque = $tab;
            $tabAttaque['DegatsDonnes']+=$MobDegatAttaqueEnvoyer;
            $tabAttaque['nbCoup']++;

        }else{
            //insertion d'une nouvelle attaque
            $req="INSERT INTO `AttaqueEntiteMob`(`idMob`, `idEntite`, `nbCoup`, `coupFatal`, `DegatsDonnes`, `DegatsReçus`) 
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

        //update AttaqueEntiteMob pour mettre a jour combien le perso a pris de degat 
        $req="UPDATE `AttaqueEntiteMob` SET 
        `DegatsDonnes`=".$tabAttaque['DegatsDonnes']."
         WHERE idMob = '".$Mob->getId()."' AND idEntite ='".$this->_id."' ";
        $Result = $this->_bdd->query($req);

        return $this->_vie;
    }

    public function setEntite($id,$nom,$vie,$degat,$vieMax,$image,$type,$lvl){
        $this->_id = $id;
        $this->_nom = $nom;
        $this->_vie = $vie;
        $this->_vieMax = $vieMax;
        $this->_degat = $degat;
        $this->_imageLien = $image;
        $this->_type = $type;
        $this->_lvl = $lvl;

        //select les Equipement déjà présent
        
        $req  = "SELECT idEquipement FROM `EntiteEquipement` WHERE idEntite='".$id."'";
        $Result = $this->_bdd->query($req);
        while($tab=$Result->fetch()){
            array_push($this->sacEquipements,$tab[0]);
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
        foreach ($this->getEquipements() as $value) {
            $valeur+=$value->getValeur();
        }

        $valeur = 100;

        return  $valeur;
    }

    //retourne toute la mécanique d'affichage d'un Entite
    public function renderHTML(){
        if ($this->_vieMax<0 || $this->_vieMax=="0" ){
            $this->_vieMax=10;
        }
        $pourcentage = round(100*$this->_vie/$this->_vieMax);
        ?>
        
        <div>
            <?php echo $this->_nom ?>( <?php echo $this->getValeur() ?> NFT) lvl  <?php echo $this->_lvl ?>
        </div>
        <div>
            <img class="Entite" src="<?php echo $this->_imageLien;?>">
        </div>
        <div class="attaque" id="attaqueEntiteValeur<?php echo $this->_id ;?>"> <?php echo $this->_degat ;?>  </div> 
        <div class="barreDeVie" id="vieEntite<?php echo $this->_id ;?>">
                <div class="vie" id="vieEntiteValeur<?php echo $this->_id ;?>" style="width: <?php echo $pourcentage?>%;">♥️<?php echo $this->_vie ;?></div>
        </div>
        

        <?php
    }

    public function getId(){
        return $this->_id;
    }

    public function getMap(){
        return $this->map;
    }

   
    public function lvlupAttaque($attaque){
        $this->_degat += $attaque;
        $sql = "UPDATE `Entite` SET `degat`='".$this->_degat."' WHERE `id`='".$this->_id."'";
        $this->_bdd->query($sql);
    }
    public function lvlupVie($viemore){
        $this->_vie += $viemore;
        $sql = "UPDATE `Entite` SET `vie`='".$this->_vie."' WHERE `id`='".$this->_id."'";
        $this->_bdd->query($sql);
    }
    public function lvlupVieMax($viemore){
        $this->_vieMax += $viemore;
        $sql = "UPDATE `Entite` SET `vieMax`='".$this->_vieMax."' WHERE `id`='".$this->_id."'";
        $this->_bdd->query($sql);
    }

    //permet de changer la position du joueur sur la carte
    public function changeMap($NewMap){
        $this->map = $NewMap;
        //on mémorise çà en base
        $sql = "UPDATE `Entite` SET `idMap`='".$NewMap->getId()."' WHERE `id`='".$this->_id."'";
        $this->_bdd->query($sql);
    }



    public function setEntiteById($id){
        $Result = $this->_bdd->query("SELECT * FROM `Entite` WHERE `id`='".$id."' ");
        if($tab = $Result->fetch()){ 
            $this->setEntite($tab["id"],$tab["nom"],$tab["vie"],$tab["degat"],$tab["vieMax"],$tab["lienImage"],$tab["type"],$tab["lvl"]);
            //recherche de sa position
            $map = new map($this->_bdd);
            $map->setMapByID($tab["idMap"]);
            $this->map = $map;

             //select les items déjà présent
            $req  = "SELECT idEquipement FROM `EntiteEquipement` WHERE idEntite='".$id."'";
            $Result = $this->_bdd->query($req);
            while($tab=$Result->fetch()){
                array_push($this->sacEquipements,$tab[0]);
            }
        }
    }

    public function setEntiteByIdWithoutMap($id){
        $Result = $this->_bdd->query("SELECT * FROM `Entite` WHERE `id`='".$id."' ");
        if($tab = $Result->fetch()){ 
            $this->setEntite($tab["id"],$tab["nom"],$tab["vie"],$tab["degat"],$tab["vieMax"],$tab["lienImage"],$tab["type"],$tab["lvl"]);
        }
    }


    //Retourne un formulaire HTML pourcreer un entite
    //et permet d'attribuer automatiquement à user
    // retour un objet entite
    public function CreateEntite($nom, $vie, $degat, $idMap,$vieMax,$lienImage,$idUser,$type,$lvl){
        
        $newperso = new Entite($this->_bdd);
        $this->_nom=htmlentities($nom, ENT_QUOTES);
        $this->_lvl = $lvl;
        $this->_imageLien=$lienImage;
        $req="INSERT INTO `Entite`(`nom`, `vie`, `degat`, `idMap`,`vieMax`,`lienImage`,`idUser`,`type`,`lvl`) 
        VALUES ('".$this->_nom."','.$vie.','.$degat.','.$idMap.','.$vieMax.','".$this->_imageLien."','".$idUser."','.$type.','.$lvl.')";
        $this->_bdd->beginTransaction();
        $Result = $this->_bdd->query($req);
        $this->_id = $this->_bdd->lastInsertId();
        if($this->_id ){ 
            $newperso->setEntiteById($this->_id );
            $this->_bdd->commit();
            return $newperso;
        }else{
            $this->_bdd->rollback();
            return null;
        }
        

        return null;
    }

    //Retourne une liste HTML de tous les entites
    //et permet d'attribuer un perso à un user
    // retour un objet entite
    public function getChoixEntite($idUser){
        if (isset($_POST["idEntite"])){
            $this->setEntiteById($_POST["idEntite"]);
            if($this->_vie==0){
                $this->resurection();
            }
            //si le entite est mort on le place ne origine 0,0
        }
        $Result = $this->_bdd->query("SELECT * FROM `Entite` where idUser='".$idUser."' ");
        ?>
        <form action="" method="post" onchange="this.submit()">
            <select name="idEntite" id="idEntite">
            <option value="">Choisir un entite</option>
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