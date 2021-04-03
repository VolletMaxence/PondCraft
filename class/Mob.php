<?php
//TODO MOB ET PERSONNAGE ON TROP DE SIMILITUDE 
//IL FAUT REFACTORISER AVEC DE LhERITAGE

class Mob extends Entite{

    private $_coefXP;
   
    public function __construct($bdd){
        Parent::__construct($bdd);
    }
    
   
    public function getCoefXp(){
        return $this->_coefXP;
    }

    public function setMob($id,$type,$nom,$degat,$vie,$coefXP,$vieMax,$idProprio){
        Parent::setMob($id,$type,$nom,$degat,$vie,$coefXP,$vieMax,$idProprio,2);
    }

    public function setMobById($id){
        Parent::setEntiteByIdWithoutMap($id);
    }

    public function setMobByIdWithMap($id){
        Parent::setEntiteById($id);
    }


    //methode appelé quand un personnage attaque un mob 
    //le perso est passé en param
    public function SubitDegat($Personnage){
        $this->_vie = $this->_vie - $Personnage->getAttaque();

        $coupFatal = 0;
        if($this->_vie<0){
            $this->_vie=0;
            $coupFatal=1;

            //on va attribuer le mob au perssonage sa vie revient a fond pour le propriétaire
            $req  = "UPDATE `Mob` SET `vie`='".$this->_vieMax."',`idPersoProprio`='".$Personnage->getId()."' WHERE `id` = '".$this->_id ."'";
            $Result = $this->_bdd->query($req);

        }else{
            $req  = "UPDATE `Mob` SET `vie`='".$this->_vie ."' WHERE `id` = '".$this->_id ."'";
            $Result = $this->_bdd->query($req);
        }

        //on va rechercher l'historique
        $req  = "SELECT * FROM `AttaquePersoMob` where idMob = '".$this->_id."' and idPersonnage = '".$Personnage->getId()."'" ;
        $Result = $this->_bdd->query($req);
        $tabAttaque['nbCoup']=0;
        $tabAttaque['DegatsDonnes']=0;
        $tabAttaque['DegatsReçus']=$Personnage->getAttaque();
        if($tab=$Result->fetch()){
            $tabAttaque = $tab;
            $tabAttaque['DegatsReçus']+=$Personnage->getAttaque();
            $tabAttaque['nbCoup']++;

        }else{
            //insertion d'une nouvelle attaque
            $req="INSERT INTO `AttaquePersoMob`(`idMob`, `idPersonnage`, `nbCoup`, `coupFatal`, `DegatsDonnes`, `DegatsReçus`) 
            VALUES (
                '".$this->_id."','".$Personnage->getId()."',1,0,0,".$tabAttaque['DegatsReçus']."
            )";
            $Result = $this->_bdd->query($req);
        }

        //update AttaquePersoMob
        $req="UPDATE `AttaquePersoMob` SET 
        `nbCoup`=".$tabAttaque['nbCoup'].",
        `coupFatal`=".$coupFatal.",
        `DegatsReçus`=".$tabAttaque['DegatsReçus']."
         WHERE idMob = '".$this->getId()."' AND idPersonnage ='".$Personnage->getId()."' ";
            $Result = $this->_bdd->query($req);
        return $this->_vie;
    }

    public function getHistoriqueAttaque(){
        $req  = "SELECT * FROM `AttaquePersoMob` where idMob = '".$this->_id."'" ;
        $Result = $this->_bdd->query($req);
        while($tab=$Result->fetch()){
            array_push($this->$HostoriqueAttaque,$tab);
        }
        return $this->$HostoriqueAttaque;
    }

   

   

    //retourne toute la mécanique d'affichage d'un mob
    public function renderHTML(){
        if ($this->_vieMax<0 || $this->_vieMax=="0" ){
            $this->_vieMax=10;
        }
        $pourcentage = round(100*$this->_vie/$this->_vieMax);
        ?>
        <div class="mob">
            <div>
            <?php echo $this->_nom ?>
            ( x<?php echo $this->getCoefXp() ?> xp)
            </div>
            <div class="attaque" id="attaqueMobValeur<?php echo $this->_id ;?>"><?php echo $this->_degat ;?></div>
            <div class="barreDeVie" id="vieMob<?php echo $this->_id ;?>">
                <div class="vie" id="vieMobValeur<?php echo $this->_id ;?>" style="width: <?php echo $pourcentage?>%;">♥️<?php echo $this->_vie ;?></div>
            </div>
        </div>
        <?php
    }

    public function CreateMobAleatoire($map){
            $newMob = new Mob($this->_bdd);
            $type = $this->getTypeAleatoire();
            $lvl = $map->getlvl();
            $coefAbuseVie = rand(20,50);
            $coefAbuseArme = rand(2,20);
            $vie = $coefAbuseVie*$type[2]*$lvl*$lvl;
            $degat = $coefAbuseArme*$type[2]*$lvl;
            //Menir 
            if($type[1]==0){
                $vie = $coefAbuseVie*20*$lvl*$lvl;
                $degat = 1;
            }

            $newMob->CreateEntite($this->generateNom($type[0]), $vie, $degat, $map->getId(),$vie,'',null,2);

            $req="INSERT INTO `Mob`(`coefXp`, `id`) 
            VALUES ('".$type[2]."','".$this->_id."')";
            $Result = $this->_bdd->query($req);
           
            if( $newMob->getId()){ 
                $newMob->setMobById( $newMob->getId());
                return $newMob;
            }else{
                return null;
            }
            $itemEnplus = new Item($this->_bdd);
            $nbItem = rand(2,$coefAbuseArme+round(($coefAbuseVie/10)));
            for($i=0;$i<$nbItem;$i++){
                    $map->addItem($itemEnplus->createItemAleatoire());   
             }
    }

    //retour un tableau vace le nom du type et id dy type
    //$tab[0]=$newTypeNom;
    //$tab[1]=$newType;
    //$tab[2]=$coef;
    private function getTypeAleatoire(){
        $req="SELECT * FROM TypeMob ORDER BY rarete ASC";
        $Result = $this->_bdd->query($req);
        $i = $Result->rowCount();
        $coef = 0;
        $imax=$i*3;
        $newType=0;
        $rarete=0;
        $newTypeNom='Menir';
        while($tab=$Result->fetch()){
           if(rand(0,$imax)<$i){
            $newType = $tab['id'];
            $newTypeNom = $tab['nom'];
            $coef=$tab['rarete'];
            break;
           }
           $i--;
        }
        $tab[0]=$newTypeNom;
        $tab[1]=$newType;
        $tab[2]=$coef;
        return $tab;
    }

    //Permet de générer un nom de map
    public function generateNom($type){
        $nom =$type;

        $Adjectif ="";
        switch (rand(0,10)){
            case 0:
                $Adjectif ="Poisseux";
            break;
            case 1:
                $Adjectif ="Luxuriant";
            break;
            case 2:
                $Adjectif ="Pas belle";
            break;
            case 3:
                $Adjectif ="Enchantée";
            break;
            case 4:
                $Adjectif ="de la mort";
            break;
            case 5:
                $Adjectif ="des nains";
            break;
            case 6:
                $Adjectif ="Du pauvre";
            break;
            case 7:
                $Adjectif ="des loups";
            break;
            case 8:
                $Adjectif ="Lumineux";
            break;
            case 9:
                $Adjectif ="Sombre";
            break;
            default:
                $Adjectif ="Noir";
        }

        $Consone ="";
        for($i=0;$i<=rand(1,3);$i++){
            switch (rand(0,19)){
                case 0:
                    $Consone .="zar";
                break;
                case 1:
                    $Consone .="dra";
                break;
                case 2:
                    $Consone .="bel";
                break;
                case 3:
                    $Consone .="cri";
                break;
                case 4:
                    $Consone .="fa";
                break;
                case 5:
                    $Consone .="zor";
                break;
                case 6:
                    $Consone .="pat";
                break;
                case 7:
                    $Consone .="for";
                break;
                case 8:
                    $Consone .="ga";
                break;
                case 9:
                    $Consone .="lon";
                break;
                case 10:
                    $Consone .="vi";
                break;
                case 11:
                    $Consone .="bu";
                break;
                case 12:
                    $Consone .="al";
                break;
                case 13:
                    $Consone .="sion";
                break;
                case 14:
                    $Consone .="teur";
                break;
                case 15:
                    $Consone .="nar";
                break;
                case 16:
                    $Consone .="pon";
                break;
                case 17:
                    $Consone .="pen";
                break;
                case 18:
                    $Consone .="ri";
                break;
                case 19:
                    $Consone .="or";
                break;
                default:
                $Consone .=" ";
            }
        }

        return $nom ." ". $Adjectif." ".$Consone;
    }

    public function generateImage(){
        $topic='creature';
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

        echo '<img src="'.$partialString3.'" widht="200px">';
    }
}
?>