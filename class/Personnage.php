<?php

class Personnage{
    
    private $_id;
    private $_nom;
    private $_vie;
    private $_vieMax;
    private $_degat;

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

    public function SubitDegat($valeur){
        $this->_vie = $this->_vie - $valeur;
        if($this->_vie<0){
            $this->_vie =0;
            //retour en zone 0,0
        }
        $req  = "UPDATE `Personnage` SET `vie`='".$this->_vie ."' WHERE `id` = '".$this->_id ."'";
        $Result = $this->_bdd->query($req);
        return $this->_vie;
    }

    public function setPersonnage($id,$nom,$vie,$degat,$vieMax){
        $this->_id = $id;
        $this->_nom = $nom;
        $this->_vie = $vie;
        $this->_vieMax = $vieMax;
        $this->_degat = $degat;

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
    }

    public function setPersonnageById($id){
        $Result = $this->_bdd->query("SELECT * FROM `Personnage` WHERE `id`='".$id."' ");
        if($tab = $Result->fetch()){ 

            $this->setPersonnage($tab["id"],$tab["nom"],$tab["vie"],$tab["degat"],$tab["vieMax"]);
            
            //recherche de sa position
            $map = new map($this->_bdd);
            $map->setMapByID($tab["idMap"]);
            $this->map = $map;
            
        }
    }

    public function setPersonnageByIdWithoutMap($id){
        $Result = $this->_bdd->query("SELECT * FROM `Personnage` WHERE `id`='".$id."' ");
        if($tab = $Result->fetch()){ 

            $this->setPersonnage($tab["id"],$tab["nom"],$tab["vie"],$tab["degat"],$tab["vieMax"]);
            
           
            
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
    public function CreatNewPersonnage(){
        
        ?>
        <form action="" method="post">
            <div>Creer un Perso ou choisie en un</div>
           <input type="text" name="NomPersonnage" required>
           <input type="submit" value="Creer" name="createPerso">
        </form>
        <?php
        if (isset($_POST["createPerso"])){
            $newperso = new Personnage($this->_bdd);
            $this->_nom=htmlentities($_POST['NomPersonnage'], ENT_QUOTES);
            $req="INSERT INTO `Personnage`(`nom`, `vie`, `degat`, `idMap`,`vieMax`) VALUES ('".$this->_nom."',10,10,0,10)";
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
            if($this->_vie==0){
                $this->resurection();
            }
            //si le personnage est mort on le place ne origine 0,0
        }

        return $this;
    }

}

?>