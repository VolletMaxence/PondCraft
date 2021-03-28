<?php
class Mob{
    private $_id;
    private $_type;
    private $_nom;
    private $_degat;
    private $_vie;
    private $_vieMax;

    //permet de donner plus ou moins xp
    private $_coefXP;

    public function __construct($bdd){
        $this->_bdd = $bdd;
    }

    public function getId(){
        return $this->_id ;
    }
    public function getNom(){
        return $this->_nom ;
    }

    public function getVie(){
        return $this->_vie ;
    }
    public function getVieMax(){
        return $this->_vieMax ;
    }

    public function getAttaque(){
        return $this->_degat;
    }
    public function getCoefXp(){
        return $this->_coefXP;
    }
    public function setMob($id,$type,$nom,$degat,$vie,$coefXP,$vieMax){
        $this->_id = $id;
        $this->_type = $type;
        $this->_nom = $nom;
        $this->_degat = $degat;
        $this->_vie = $vie;
        $this->_coefXP = $coefXP;
        $this->_vieMax = $vieMax;

         
    }
    public function SubitDegat($valeur){
        $this->_vie = $this->_vie - $valeur;
        if($this->_vie<0){
            $this->_vie =0;
        }
        $req  = "UPDATE `Mob` SET `vie`='".$this->_vie ."' WHERE `id` = '".$this->_id ."'";
        $Result = $this->_bdd->query($req);
        return $this->_vie;
    }


    //cette méthode ne charge pas la MAP du mob
    public function setMobById($id){
        $Result = $this->_bdd->query("SELECT * FROM `Mob` WHERE `id`='".$id."' ");
        if($tab = $Result->fetch()){ 
            $this->setMob($tab["id"],$tab["type"],$tab["nom"],$tab["degat"],$tab["vie"],$tab["coefXp"],$tab["vieMax"]);   
        }
    }

    //cette méthode  charge la map en plus
    public function setMobByIdWithMap($id){
        $Result = $this->_bdd->query("SELECT * FROM `Mob` WHERE `id`='".$id."' ");
        if($tab = $Result->fetch()){ 

            $this->setMob($tab["id"],$tab["type"],$tab["nom"],$tab["degat"],$tab["vie"],$tab["coefXp"],$tab["vieMax"]);
            
            //recherche de sa position
            $map = new map($this->_bdd);
            $map->setMapByID($tab["idMap"]);
            $this->map = $map;
            
        }
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
            $vie = rand(50,100)*$type[2]*$lvl*3;
            $req="INSERT INTO `Mob`(`nom`,`type`, `vie`, `degat`, `idMap` , `coefXp` ,  `vieMax`) 
            VALUES ('".$this->generateNom($type[0])."',
                    ".$type[1]."
                    ,".$vie."
                    ,".rand(10,100)*$type[2]*$lvl."
                    ,".$map->getId()."
                    ,".$type[2]."
                    ,".$vie."
                    )";
            $this->_bdd->beginTransaction();
            $Result = $this->_bdd->query($req);
            $lastID = $this->_bdd->lastInsertId();
            if($lastID){ 
                $newMob->setMobById($lastID);
                $this->_bdd->commit();
                return $newMob;
            }else{
                $this->_bdd->rollback();
                return null;
            }

    }

    //retour un tableau vace le nom du type et id dy type
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