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
        
        $message="";
        $vieMax=0;
        $vie=0;
        $vieAttaquant=$Attaquant->getVie();
        $vieMaxAttaquant=$Attaquant->getVieMax();

        
                //attaque sur perso
            if($_GET["type"]==0 ){

                $Deffensseur = new Personnage($mabase);
                $Deffensseur->setPersonnageByIdWithoutMap($_GET["id"]);
                $vieMax=$Deffensseur->getVieMax();
                $vie=$Deffensseur->getVie();
                //on verrifie que le perso n'est pas mort
                if($Deffensseur->getVie()>0){
                    if($vieAttaquant!=0){
                        $vie=$Deffensseur->SubitDegat($force);
                        $vieMax = $Deffensseur->getVieMax();

                        //on va retirer le coup d'attaque de base du deffensseur
                        //car une attaque n'est pas gratuite
                        $vieAttaquant=$Attaquant->SubitDegat($Deffensseur->getAttaque());
                        if($vieAttaquant==0){
                            $message .= "ton perso est mort ";
                        }
                    }else{
                        $message .= "Tu es déjà mort tu ne peux plus attaquer ";
    
                    }
                }else{
                    $message .= "Ce perso est déjà mort ";
                }

              
            
            }

            //attaque sur mob
            if($_GET["type"]==1){
                $Deffensseur = new Mob($mabase);
                $Deffensseur->setMobByIdWithMap($_GET["id"]);
                $vieMax=$Deffensseur->getVieMax();
                $vie=$Deffensseur->getVie();
                
                if($Deffensseur->getVie()>0){
                    if($vieAttaquant!=0){
                        $vie=$Deffensseur->SubitDegat($force);
                        $vieMax = $Deffensseur->getVieMax();
                        //retour de batton le deffenseur auusi attaque
                        $vieAttaquant=$Attaquant->SubitDegat($Deffensseur->getAttaque());
                        if($vieAttaquant==0){
                            $message .= "ton perso est mort ";
                        }
                    }else{
                        $message .= "Tu es déjà mort tu ne peux plus attaquer ";
    
                    }
                }else{
                    $message .= "Ce mob est déjà mort ";
                }

                
            }
       
        

        $reponse[0]=$_GET["id"];
        $reponse[1]=$vie;
        $reponse[2]=$vieMax;
        $reponse[3]=$vieAttaquant;
        $reponse[4]=$vieMaxAttaquant;
        $reponse[5]=$Attaquant->getId();
        $reponse[6]=$message;

    }
}

 echo json_encode($reponse); 



?>