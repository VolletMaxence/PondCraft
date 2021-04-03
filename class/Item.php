<?php
class Item{

    private $_id;
    private $_type;
    private $_nom;
    private $_valeur;
    private $_efficacite;
    private $_lvl;

    private $_bdd;

    public function setItemByID($id){

        $req="SELECT * FROM Item WHERE id='".$id."' ";

        $Result = $this->_bdd->query($req);
        if($tab = $Result->fetch()){ 

            $this->setItem($tab["id"],
                          $tab["type"],
                          $tab["nom"],
                          $tab["valeur"],
                          $tab["efficacite"],
                          $tab["lvl"]);


                         
        }
    }

    public function setItem($id,$type,$nom,$valeur,$efficacite,$lvl){
        $this->_id = $id;
        $this->_nom = $nom;
        $this->_type = $type;
        $this->_valeur = $valeur;
        $this->_efficacite = $efficacite;
        $this->_lvl = $lvl;
    }

    public function getLvl(){
        return $this->_lvl;
    }

    public function getEfficacite(){
        return $this->_efficacite;
    }

    public function deleteItem($id){
        $req="DELETE FROM Item WHERE id='".$id."' ";

        $Result = $this->_bdd->query($req);
    }

    public function getNom(){
        return $this->_nom;
    }

    public function getId(){
        return $this->_id;
    }
    public function getValeur(){
        return $this->_valeur;
    }

    //retourn un tableau avec id information lienImage nom rarete
    public function getType(){

        $req="SELECT * FROM TypeItem WHERE id='".$this->_type."' ";

        $Result = $this->_bdd->query($req);
        if($tab = $Result->fetch()){ 
            return $tab;
        }else{
            return null;
        }
    }

    public function getClassRarete(){
        $req="SELECT rarete FROM TypeItem where id = '".$this->_type."'";
        $Result = $this->_bdd->query($req);
        $colorRarete = "background-color : rgba(";
        if($tab = $Result->fetch()){
            //pour le moment les raretés vont de 1 à 16
            //rareté de vert à rouge

            if($tab[0]<8){

                //on par de 0   255 0 
                //        à 255 255 0
                $val = round((($tab[0]/8)*((255-100)+100))+95);
                $colorRarete .= $val . ',255,0'; 

            }else{
                //on par de 255 255 0 
                //        à 255 0   0
                //et les valeur vont de 8 à 16
                $val = round(((($tab[0]-8)/8)*((255-100)+100))+95);
                $val = 255-$val ;
                $colorRarete .= '255,'.$val . ',0'; 
            }

        } else{
            //poussiere 
            $colorRarete .=  '255,255,255'; 

        }
        //max rarete valeur = 1600
        //1600 = 1
        $Transparence = (($this->_valeur/160)*((1-0.3)))+0.3 ;
        return $colorRarete.','.$Transparence.') !important' ;
    }

    public function __construct($bdd){
        $this->_bdd = $bdd;
    }

    public function createItemSoinConsommable(){
        $newItem = new Item($this->_bdd);
        $req="SELECT * FROM TypeItem where id = 2";
        $Result = $this->_bdd->query($req);
        if($tab=$Result->fetch()){
            $newType = $tab['id'];
            $newTypeNom = $tab['nom'];
            $rarete=$tab['rarete'];
            $getAdjectifEfficace = $this->getAdjectifEfficace($newTypeNom);
            $newNom = $getAdjectifEfficace['newNom'];
            $efficacite = $getAdjectifEfficace['efficacite'];
            $newValeur = rand(5,10)*$rarete;
            $this->_bdd->beginTransaction();
            $req="INSERT INTO `Item`( `type`, `nom`, `valeur`, `efficacite`,`lvl`) VALUES ('".$newType."','".$newNom."','".$newValeur."','".$efficacite."',1)";
            $Result = $this->_bdd->query($req);
            $lastID = $this->_bdd->lastInsertId();
            if($lastID){ 
    
                $newItem->setItem($lastID,$newType,$newNom,$newValeur,$efficacite,1);
                $this->_bdd->commit();
                return $newItem;
            }else{
                $this->_bdd->rollback();
                return null;
            }
            
        }else{
            return null;
        }

    }

    public function createItemAleatoire(){
        $newItem = new Item($this->_bdd);

        $req="SELECT * FROM TypeItem ORDER BY rarete ASC";
        $Result = $this->_bdd->query($req);
        $i = $Result->rowCount();
        $imax=$i*3;
        $newType=0;
        $rarete=0;
        $newTypeNom='poussiere';
        while($tab=$Result->fetch()){
           if(rand(0,$imax)<$i){
            $newType = $tab['id'];
            $newTypeNom = $tab['nom'];
            $rarete=$tab['rarete'];
            break;
           }
           $i--;
        }

        $getAdjectifEfficace = $this->getAdjectifEfficace($newTypeNom);
        $newNom = $getAdjectifEfficace['newNom'];
        $efficacite = $getAdjectifEfficace['efficacite'];
        
        $newValeur = rand(5,10)*$rarete;

        $this->_bdd->beginTransaction();
        $req="INSERT INTO `Item`( `type`, `nom`, `valeur`, `efficacite`,`lvl`) VALUES ('".$newType."','".$newNom."','".$newValeur."','".$efficacite."',1)";
        $Result = $this->_bdd->query($req);
        $lastID = $this->_bdd->lastInsertId();
        if($lastID){ 
            $newItem->setItem($lastID,$newType,$newNom,$newValeur,$efficacite,1);
            $this->_bdd->commit();
            return $newItem;
        }else{
            $this->_bdd->rollback();
            echo "erreur anormal createItemAleatoire item.php ".$req;
            return null;
        }
    }

    private function getAdjectifEfficace($newTypeNom){

        //generate nom
        switch (rand(0,10)) {
            case 0:
                $newNom = $newTypeNom.' cassé';
                $efficacite = 0.3;
            break;
            case 1:
                $newNom = $newTypeNom.' tout mou';
                $efficacite = 0.4;
            break;
            case 2:
                $newNom = $newTypeNom.' moisie';
                $efficacite = 0.5;
            break;
            case 3:
                $newNom = $newTypeNom.' tordu';
                $efficacite = 0.6;
            break;
            case 4:
                $newNom = $newTypeNom.' usagé';
                $efficacite = 0.7;
            break;
            case 5:
                $newNom = $newTypeNom.' moche';
                $efficacite = 0.8;
            break;
            case 6:
                $newNom = $newTypeNom.' jolie';
                $efficacite = 0.9;
            break;
            case 7:
                $newNom = $newTypeNom.' neuf';
                $efficacite = 1;
            break;
            case 8:
                $newNom = $newTypeNom.' Puissant';
                $efficacite = 1.4;
            break;
            case 9:
                $newNom = $newTypeNom.' efficasse';
                $efficacite = 1.1;
            break;
            case 10:
                $newNom = $newTypeNom.' magic';
                $efficacite = 1.2;
            break;
            default:
                $newNom = $newTypeNom.' enchantéeu';
                $efficacite = 1.3;
            break;
        }

        $reponse['newNom']=$newNom;
        $reponse['efficacite']=$efficacite;

        return $reponse;
    }
}
?>