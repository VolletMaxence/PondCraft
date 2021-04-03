<?php
class Item{

    private $_id;
    private $_type;
    private $_nom;
    private $_valeur;

    private $_bdd;

    public function setItemByID($id){

        $req="SELECT * FROM Item WHERE id='".$id."' ";

        $Result = $this->_bdd->query($req);
        if($tab = $Result->fetch()){ 

            $this->setItem($tab["id"],
                          $tab["type"],
                          $tab["nom"],
                          $tab["valeur"]);
        }
    }

    public function setItem($id,$type,$nom,$valeur){
        $this->_id = $id;
        $this->_nom = $nom;
        $this->_type = $type;
        $this->_valeur = $valeur;
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

        //generate nom
        switch (rand(0,10)) {
            case 0:
                $newNom = $newTypeNom.' cassé';
            break;
            case 1:
                $newNom = $newTypeNom.' blanc';
            break;
            case 2:
                $newNom = $newTypeNom.' usagé';
            break;
            case 3:
                $newNom = $newTypeNom.' moisie';
            break;
            case 4:
                $newNom = $newTypeNom.' neuf';
            break;
            case 5:
                $newNom = $newTypeNom.' jolie';
            break;
            case 6:
                $newNom = $newTypeNom.' moche';
            break;
            case 7:
                $newNom = $newTypeNom.' magic';
            break;
            case 8:
                $newNom = $newTypeNom.' enchantée';
            break;
            case 9:
                $newNom = $newTypeNom.' tordu';
            break;
            case 10:
                $newNom = $newTypeNom.' qui sent bon';
            break;
            default:
                $newNom = $newTypeNom.' qui pu';
            break;
        }
        $newValeur = rand(5,10)*$rarete;

        $this->_bdd->beginTransaction();
        $req="INSERT INTO `Item`( `type`, `nom`, `valeur`) VALUES ('".$newType."','".$newNom."','".$newValeur."')";
        $Result = $this->_bdd->query($req);
        $lastID = $this->_bdd->lastInsertId();
        if($lastID){ 

            $newItem->setItem($lastID,$newType,$newNom,$newValeur);
            $this->_bdd->commit();
            return $newItem;
        }else{
            $this->_bdd->rollback();
            return null;
        }
    }
}
?>