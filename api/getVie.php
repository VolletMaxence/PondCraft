<?php //cette api doit etre lancé pour attaquer un id
//cette API retourne un tableau avec idDuPersoattaque, sa vie restant et sa vie de base
        // cette api retour un tableau avec 0 si elle n'a pas eccecuter le code attendu
session_start();
include "../fonction.php"; 
$reponse[0]=0;
if($access){
    if(isset($_GET["id"]) && isset($_GET["type"])){

        //on récupere la force du perso en cours
        $Attaquant = $Joueur1->getPersonnage();
        $force = $Attaquant->getAttaque();
        $vieMax=0;
        $vie=0;
        $vieAttaquant=0;
        $vieMaxAttaquant=$Attaquant->getVieMax();

        //attaque sur perso
        if($_GET["type"]==0){

            $Deffensseur = new Personnage($mabase);
            $Deffensseur->setPersonnageByIdWithoutMap($_GET["id"]);
            //on verrifie que le perso n'est pas mort
            if($Deffensseur->getVie()>0){
                $vie=$Deffensseur->SubitDegat($force);
                $vieMax = $Deffensseur->getVieMax();
            }

            //on va retirer le coup d'attaque de base du deffensseur
            //car une attaque n'est pas gratuite
            $vieAttaquant=$Attaquant->SubitDegat($Deffensseur->getAttaque());
            
           
        }

        //attaque sur mob
        if($_GET["type"]==1){
            $Deffensseur = new Mob($mabase);
            $Deffensseur->setMobByIdWithMap($_GET["id"]);
            $vie =$Deffensseur->SubitDegat($force);
            $vieMax = $Deffensseur->getVieMax();

            //retour de batton le deffenseur auusi attaque
            $vieAttaquant=$Attaquant->SubitDegat($Deffensseur->getAttaque());
        }

        $reponse[0]=$_GET["id"];
        $reponse[1]=$vie;
        $reponse[2]=$vieMax;
        $reponse[3]=$vieAttaquant;
        $reponse[4]=$vieMaxAttaquant;
        $reponse[5]=$Attaquant->getId();

    }
}

 echo json_encode($reponse); 



?>