<?php
class map{

    private $_id;
    private $_nom;

    //la position initial est 0
    //les autres sont des hash
    private $_position=0;

    //lien vers les map adjacentes.
    private $mapNord=null;
    private $mapSud=null;
    private $mapEst=null;
    private $mapOuest=null;

    public function __construct($bdd){
        $this->_bdd = $bdd;
    }

    public function setMap($id,$nom,$position,$mapNord,$mapSud,$mapEst,$mapOuest){
        $this->_id = $id;
        $this->_nom = $nom;
        $this->_position = $position;
        
        //je place les id pour ne pas que l'objet racupère en récurciv toute les maps inclu dans elle meme
        (is_null($mapNord))?$this->mapNord = null:$this->mapNord = $mapNord;
        (is_null($mapSud))?$this->mapSud = null:$this->mapSud = $mapSud;
        (is_null($mapEst))?$this->mapEst = null:$this->mapEst = $mapEst;
        (is_null($mapOuest))?$this->mapOuest = null:$this->mapOuest = $mapOuest;
      
    }

    //accesseur get set 
    public function getId(){
        return $this->_id;
    }
    public function getNom(){
        return $this->_nom;
    }


    //je vais chercher l'objet map au moment ou j'ai besoin
    //sinon les map vont automatiquement chercher leur map adjacente et çà va ramer 
    public function getPosition(){
        return $this->_position;
    }
    public function getMapNord(){
        if(is_null($this->mapNord)){return null;}
        $map = new Map($this->_bdd) ;
        $map->setMapByID($this->mapNord);
        return $map;
    }
    public function getMapSud(){
        if(is_null($this->mapSud)){return null;}
        $map = new Map($this->_bdd) ;
        $map->setMapByID($this->mapSud);
        return $map;
    }
    public function getMapEst(){
        if(is_null($this->mapEst)){return null;}
        $map = new Map($this->_bdd) ;
        $map->setMapByID($this->mapEst);
        return $map;
    }
    public function getMapOuest(){
        if(is_null($this->mapOuest)){return null;}
        $map = new Map($this->_bdd) ;
        $map->setMapByID($this->mapOuest);
        return $map;
    }
    public function setMapNord($NewMap){
        $this->mapNord = $NewMap;
        //update en base
        $req="UPDATE `map` SET `mapNord`='".$NewMap->getId()."'  WHERE `id` = '$this->_id'";
        $Result = $this->_bdd->query($req);
        
    }
    public function setMapSud($NewMap){
        $this->mapSud = $NewMap;
        //update en base
        $req="UPDATE `map` SET `mapSud`='".$NewMap->getId()."'  WHERE `id` = '$this->_id'";
        $Result = $this->_bdd->query($req);
        
    }
    public function setMapEst($NewMap){
        $this->mapEst = $NewMap;
        //update en base
        $req="UPDATE `map` SET `mapEst`='".$NewMap->getId()."'  WHERE `id` = '$this->_id'";
        $Result = $this->_bdd->query($req);
        
    }
    public function setMapOuest($NewMap){
        $this->mapOuest = $NewMap;
        //update en base
        $req="UPDATE `map` SET `mapOuest`='".$NewMap->getId()."'  WHERE `id` = '$this->_id'";
        $Result = $this->_bdd->query($req);
        
    }
    //il faut lui donner la map adjacente
    //String : lui dire si elle est par rapport à elle au sud , nord , est ou ouest ($cardinalite)
    //int id du user qui as decouvert cette map en premier
    public function Create($map,$cardinalite,$idUserDecouverte){

       
        if(intval($map->getId())>=0){
            
        }else{
            //la map n'existe pas
            return null;
        }

        $mapSud = 'NULL' ;
        $mapNord= 'NULL';
        $mapOuest = 'NULL';
        $mapEst = 'NULL';

        //IMPORTANT IL Faut vérifier que la zone qu'on découvre n'existe pas déjà par un autre chemin 
        //on va donc parcourir tous les autres chemin existant pour voir si on arrive au meme endroit.
        //ca va donc etre un chemin recurcif.
        // A= -1pts quand on va a l'est +1 quand on va a ouest
        // B= 1pts quand tu vas au nord -1 point quand tu vas au sud.
        // si on trouve A = 1 quand on va au nord et B=0 alors on a déjà une map au nord
        // si on trouve A = -1 quand on va au sud et B=0 alors on a déjà une map au sud
        // si on trouve B = -1 quand on va  a l'ouest et A=0 alors on a déjà une map au Ouest
        // si on trouve B = 1 quand on va  a l'est et A=0 alors on a déjà une map a l'est
     
        switch ($cardinalite) {
            case "nord":
                $mapSud = "'".$map->getId()."'";
                //on vérifie si la map n'existe pas déjà a cette cardinalité
                if(!is_null($map->getMapNord())){
                    echo " la map existe dejà au nord ".$map->getMapNord()->getPosition();
                    return $map->getMapNord();
                }
                break;
            case "sud":
                $mapNord = "'".$map->getId()."'";
                if(!is_null($map->getMapSud())){
                    echo " la map existe dejà au sud ".$map->getMapSud()->getPosition();
                    return $map->getMapSud();
                }
        
                break;
            case "ouest":
                $mapOuest = "'".$map->getId()."'";
                if(!is_null($map->getMapOuest())){
                    echo "la map existe dejà a est ".$map->getMapOuest()->getPosition();
                    return $map->getMapOuest();
                }

                break;
            case "est":
                $mapEst = "'".$map->getId()."'";
                if(!is_null($map->getMapEst())){
                    echo "la map existe dejà à l'ouest ".$map->getMapEst()->getPosition();
                    return $map->getMapEst();
                }
          
                break;
             default:
             
                return null;
              
                
        }

        $mapExistante = $this->trouveRacourcie(0,0,$cardinalite, array());
        if(is_object($mapExistante)){
            echo "<p>Ajout d'un raccourcie grace à toi entre la map N°".$this->getPosition()." et ".$mapExistante->getPosition()."</p>";
            
            switch ($cardinalite) {
                case "nord":
                    $req="UPDATE `map` SET `mapSud`='".$this->getId()."' WHERE `id` = '".$mapExistante->getId()."'";
                    $this->setMapNord($mapExistante);
                    break;
                case "sud":
                    $req="UPDATE `map` SET `mapNord`='".$this->getId()."' WHERE `id` = '".$mapExistante->getId()."'";
                    $this->setMapSud($mapExistante);
                    break;
                case "est":
                    $req="UPDATE `map` SET `mapOuest`='".$this->getId()."' WHERE `id` = '".$mapExistante->getId()."'";
                    $this->setMapEst($mapExistante);
                    break;
                case "ouest":
                    $req="UPDATE `map` SET `mapEst`='".$this->getId()."' WHERE `id` = '".$mapExistante->getId()."'";
                    $this->setMapOuest($mapExistante);
                    break;
            }
            $Result = $this->_bdd->query($req);
            return $mapExistante;
        }

        
        

        $position = $this->generatePosition();
        $nom = $this->generateNom();


        //insertion en base
        //la position doit etre unique
        
        $req="INSERT INTO `map`( `nom`, `position`, `mapNord`, `mapSud`, `mapEst`, `mapOuest`) 
                VALUES 
              ('".$nom."','".$position."',".$mapNord.",".$mapSud.",".$mapEst.",".$mapOuest.")";
        
        $Result = $this->_bdd->query($req);

        $req="select id from map where position='".$position."'";
        $Result = $this->_bdd->query($req);
        if($tab = $Result->fetch()){ 
            $newmap = new map($this->_bdd);
            $newmap->setMapByID($tab["id"]);

            //on met à jour l'ancienne map avec les coordonnée de la nouvelle
            switch ($cardinalite) {
                case "nord":
                    $map->setMapNord($newmap);
                    break;
                case "sud":
                    $map->setMapSud($newmap);
                    break;
                case "est":
                    $map->setMapEst($newmap);
                    break;
                case "ouest":
                    $map->setMapOuest($newmap);
                    break;
            }

            return $newmap;
        }
        return null;
    }

    //retourn un string 
    //hash aléatoire pour une nouvelle position
    public function generatePosition(){
        return hash('ripemd160', $this->generateNom().rand(0,100000000).rand(0,100000000));
    }

    
    public function setMapByPosition($position){
        $Result = $this->_bdd->query("SELECT id FROM `map` WHERE `position`='".$position."' ");
        if($tab = $Result->fetch()){ 
            $this->setMapByID($tab["id"]);
        }
    }

    public function setMapByID($id){
        $Result = $this->_bdd->query("SELECT * FROM `map` WHERE `id`='".$id."' ");
        if($tab = $Result->fetch()){ 

            $this->setMap($tab["id"],
                          $tab["nom"],
                          $tab["position"],
                          $tab["mapNord"],
                          $tab["mapSud"],
                          $tab["mapEst"],
                          $tab["mapOuest"]);
        }
    }

    //retourne les liens HTML des 4 map adjacente
    public function getMapAdjacenteLienHTML(){
        ?>
        <div class="MapAdjacente">
        <?php 

            $Mnord = $this->getMapNord();
            $Msud = $this->getMapSud();
            $Mest = $this->getMapEst();
            $Mouest = $this->getMapOuest();


            if(!is_null($Mnord)){
                ?>
                Nord : <div class="MapAdjacenteNord"><a href="map.php?position=<?php echo $Mnord->getPosition()?>&cardinalite=nord"><?php echo $Mnord->getNom()?></a></div>
                <?php
            }else{
                ?>
                Nord : <div class="MapAdjacenteNord"><a href="map.php?position=Generate&cardinalite=nord">Region Inconu</a></div>
                <?php
            }
            if(!is_null($Msud)){
                ?>
                Sud : <div class="MapAdjacenteSud"><a href="map.php?position=<?php echo $Msud->getPosition()?>&cardinalite=sud"><?php echo $Msud->getNom()?></a></div>
                <?php
            }else{
                ?>
                Sud : <div class="MapAdjacenteSud"><a href="map.php?position=Generate&cardinalite=sud">Region Inconu</a></div>
                <?php
            }
            if(!is_null($Mest)){
                ?>
                Est : <div class="MapAdjacenteEst"><a href="map.php?position=<?php echo $Mest->getPosition()?>&cardinalite=est"><?php echo $Mest->getNom()?></a></div>
                <?php
            }else{
                ?>
                Est : <div class="MapAdjacenteEst"><a href="map.php?position=Generate&cardinalite=est">Region Inconu</a></div>
                <?php
            }
            if(!is_null($Mouest)){
                ?>
                Ouest : <div class="MapAdjacenteOuest"><a href="map.php?position=<?php echo $Mouest->getPosition()?>&cardinalite=ouest"><?php echo $Mouest->getNom()?></a></div>
                <?php
            }else{
                ?>
                Ouest : <div class="MapAdjacenteOuest"><a href="map.php?position=Generate&cardinalite=ouest">Region Inconu</a></div>
                <?php
            }
        
        ?>
       
        </div>

        <?php

    }

    //retourn un tableau d'objet de 4 map 
    public function getMapAdjacente(){  
        $tabMapAdjacent = array();
        array_push($tabMapAdjacent, $this->mapNord);
        array_push($tabMapAdjacent, $this->mapSud);
        array_push($tabMapAdjacent, $this->mapEst);
        array_push($tabMapAdjacent, $this->mapOuest);
    }


    //Permet de générer un nom de map
    public function generateNom(){
        $nom ="";
        switch (rand(0,10)){
            case 0:
                $nom ="La Foret";
            break;
            case 1:
                $nom ="L'Ocean";
            break;
            case 2:
                $nom ="La Montagne";
            break;
            case 3:
                $nom ="Le Marai";
            break;
            case 4:
                $nom ="La Ville";
            break;
            case 5:
                $nom ="Le Village";
            break;
            case 6:
                $nom ="La Cambrousse";
            break;
            case 7:
                $nom ="La Plaine";
            break;
            case 8:
                $nom ="Le Dongeon";
            break;
            case 9:
                $nom ="La Campagne";
            break;
            default:
                $nom ="Le Chemin";


        }
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
        for($i=0;$i<rand(1,4);$i++){
            switch (rand(0,19)){
                case 0:
                    $Consone .="bien";
                break;
                case 1:
                    $Consone .="dra";
                break;
                case 2:
                    $Consone .="bel";
                break;
                case 3:
                    $Consone .="con";
                break;
                case 4:
                    $Consone .="car";
                break;
                case 5:
                    $Consone .="den";
                break;
                case 6:
                    $Consone .="feu";
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
                    $Consone .="la";
                break;
                case 11:
                    $Consone .="len";
                break;
                case 12:
                    $Consone .="mon";
                break;
                case 13:
                    $Consone .="ma";
                break;
                case 14:
                    $Consone .="ni";
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


    //fonction de recherche récursive de map adjacent
    //retourne true s'il a trouvé un chemin
    //selon le resultat de ScoreNordSud et ScoreEstOuest
    //la $TabBloquante permet de ne pas revenir sur ses chemins
    public function trouveRacourcie($ScoreNordSud,$ScoreEstOuest,$cardinalite,$TabBloquante){
        
        switch ($cardinalite) {
            case "nord":
                if($ScoreNordSud == 1 && $ScoreEstOuest == 0){
                    echo "<p>on a trouvé un autre chemin !!!</p>";
                    //je retourne la map dejà en place
                    return $this;
                }
                break;
            case "sud":
                if($ScoreNordSud == -1 && $ScoreEstOuest == 0){
                    echo "<p>on a trouvé un autre chemin !!!</p>";
                    //je retourne la map dejà en place
                    return $this;
                }
                break;
            case "est":
                if($ScoreNordSud == 0 && $ScoreEstOuest == 1){
                    echo "<p>on a trouvé un autre chemin !!!</p>";
                    //je retourne la map dejà en place
                    return $this;
                }
                break;
            case "ouest":
                if($ScoreNordSud == 0 && $ScoreEstOuest == -1){
                    echo "<p>on a trouvé un autre chemin !!!</p>";
                    //je retourne la map dejà en place
                    return $this;
                }
                break;
        }
        //si plus aucun chemin on n'arrete la recherche est on retourne false.
        //ScoreNordSud +1 au nord -1 au sud
        //ScoreEstOuest +1 à l'est -1 à l'ouest
        //$TabBloquante permet de ne pas retourner de la ou l'on vient
        
        if(!is_null($this->mapNord) && !in_array($this->getMapNord()->getPosition(), $TabBloquante)){
            //echo "<p>recherche en nord ".$this->getMapNord()->getNom()." position : ".$this->getMapNord()->getPosition()." score N :".$ScoreNordSud." E:".$ScoreEstOuest."</p>";
            //cardinalite bloquand je vien de nord donc je bloque aprés le sud
            array_push($TabBloquante,$this->getMapNord()->getPosition());
            
        
            $TabBloquante= $this->getMapNord()->trouveRacourcie($ScoreNordSud++,$ScoreEstOuest,$cardinalite,$TabBloquante);
            //si c'est un objet c'est qu'on a trouvé la map
            if(is_object($TabBloquante)){
                return $TabBloquante;
            }
        }
        if(!is_null($this->mapSud ) && !in_array($this->getMapSud()->getPosition(), $TabBloquante) ){
            //echo "<p>recherche en sud ".$this->getMapSud()->getNom()." position : ".$this->getMapSud()->getPosition()."score N :".$ScoreNordSud." E:".$ScoreEstOuest."</p>";
            array_push($TabBloquante,$this->getMapSud()->getPosition());
            $TabBloquante= $this->getMapSud()->trouveRacourcie($ScoreNordSud--,$ScoreEstOuest,$cardinalite,$TabBloquante);
            if(is_object($TabBloquante)){
                return $TabBloquante;
            }
        }
        if(!is_null($this->mapEst ) && !in_array($this->getMapEst()->getPosition(), $TabBloquante)){
            //echo "<p>recherche en Est ".$this->getMapEst()->getNom()." position : ".$this->getMapEst()->getPosition()."score N :".$ScoreNordSud." E:".$ScoreEstOuest."</p>";
            array_push($TabBloquante,$this->getMapEst()->getPosition());
            $TabBloquante= $this->getMapEst()->trouveRacourcie($ScoreNordSud,$ScoreEstOuest--,$cardinalite,$TabBloquante);
            if(is_object($TabBloquante)){
                return $TabBloquante;
            }
        }
        if(!is_null($this->mapOuest) && !in_array($this->getMapOuest()->getPosition(), $TabBloquante)){
            //echo "<p>recherche en Ouest ".$this->getMapOuest()->getNom()." position : ".$this->getMapOuest()->getPosition()."score N :".$ScoreNordSud." E:".$ScoreEstOuest."</p>";
            array_push($TabBloquante,$this->getMapOuest()->getPosition());
            $TabBloquante= $this->getMapOuest()->trouveRacourcie($ScoreNordSud,$ScoreEstOuest++,$cardinalite,$TabBloquante);
            if(is_object($TabBloquante)){
                return $TabBloquante;
            }
        }

        
        return $TabBloquante;
    }

}

