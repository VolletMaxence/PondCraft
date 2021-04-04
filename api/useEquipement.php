<?php
session_start();
//cette api retourne aprés usage d'une equipement l'atttaque la vie et la vie max 
//elle retourn 0 si çà c'est pas bien passé
include "../session.php"; 
$reponse[0]=0;
$reponse[1]=0;
if($access){
    if(isset($_GET["idEquipement"])){

        //on doit toujours vérifier en bdd la posibilité de l'appel de API
        //iici on va utiliser un equipement pour un personnage.
        $message ='';
        $reponse[0]=0;
        $reponse[1]=0;
        $Perso = $Joueur1->getPersonnage();
        if($Perso->getVie()==0){
            $Perso->resurection();
            $reponse[1]="ton perso est mort";
        }
 
        //une fois que j'ai mes objet je vérifie que le perso possède bien equipement
        foreach ($Perso->getEquipements()  as $equipement) {
            if($_GET["idEquipement"]==$equipement->getId()){

                //on retire l'equipement du perso
                $Perso->removeEquipementById($equipement->getId());

                //selon l'id du type on fait un truc différent
                $type = $equipement->getType();
                switch ($type['id']) {
                    /*case 2:
                        $calcul = $equipement->getEfficacite()*$equipement->getLvl()*$equipement->getValeur();
                        $valeur = $Perso->SoinPourcentage($calcul);
                        $message = $Perso->getNom()." à été soigné de ".$valeur."pts de vie avec une efficacite de ".$calcul."%";
                        break;*/
                    
                    default:
                        $viemore=$equipement->getValeur();
                        $attaque=round($viemore/2);
                        $message = $Perso->getNom()." à utilisé un Equipement pour booster ses stats ";
                        $Perso->lvlupAttaque($attaque);
                        $Perso->lvlupVie($viemore);
                        $Perso->lvlupVieMax($viemore);
                        break;
                }

                

                $reponse[4]=$message;
                $reponse[3]=$Perso->getVieMax();
                $reponse[2]=$Perso->getVie();
                $reponse[1]=$Perso->getAttaque();
                $reponse[0]=$Perso->getId();
            }
        }  
    }
}
echo json_encode($reponse);
?>