<?php 
session_start();

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/map.css">
    <link rel="stylesheet" href="css/perso.css">
    <link rel="stylesheet" href="css/item.css">
    <script src="main.js"></script>
    <title>Combat</title>
</head>
<body>
    <div class="centragePrincipal">
    <?php
    include "session.php"; 
    
    if($access){
        $access = $Joueur1->deconnectToi();
    }

    if($access){
        
        //gestion accès map:
             
            $Personnage = $Joueur1->getPersonnage();
            if(is_null($Personnage->getId())){
                echo '<p>Il faut créer un personnage d\'abord.</p>';
                echo '<p><a href="index.php">Retour à l\'origine du tout</a></p>';
            }else{
                echo '<p><a href="index.php">Retour à l\'origine du tout</a></p>';
                echo "<div>";
                if(isset($_GET["position"]) && $_GET["position"]==='Generate'){
                    //TODO lol la fleme de faire la negation de ce if
                }else{
                    echo "Tu peux appeler un autre personnage.";
                    $Personnage->getChoixPersonnage($Joueur1->getId());
                    $Joueur1->setPersonnage($Personnage);
                }


                //AFFICHAGE de l'entete d'un hero
                include "ihm/affichagePersoEtSac.php";

                //AFFICHAGE d'UN TOOLTIP
                include "ihm/affichageTooltip.php";
                
                //CHARGEMENT  DE LA MAP
                include "ihm/chargementDeLaMap.php";


                //HTML  DE LA MAP
                echo '<div class="lamap">';

                    echo $BousoleDeplacement['nord'];
                   
                    echo '<div class="mapOuest">';
                        echo $BousoleDeplacement['ouest'];
                        echo '<div class="mapEst">';
                            echo '<div class="mapCentre">';
                    
                                $Joueur1->getVisitesHTML(6);
                                echo '<div class="infoMap">';
                                    echo $map->getInfoMap();
                                echo '</div>';
                                //affichage des autres joueurs sur la carte
                                include "ihm/affichageAutrePersos.php";
                           
                                //affiche les mob enemie et capturé;
                                include "ihm/affichageItemsMap.php";
                            
                                //AFFICHAGE DES ITEMS DE LA MAP
                                include "ihm/affichageTousLesMobs.php";

                            echo '</div>' ;//DIV MAP CENTRE; 
                            echo $BousoleDeplacement['est'];
                        echo '</div>' ;//DIV MAP EST; 
                    echo '</div>' ;//DIV MAP OUEST; 
                    echo $BousoleDeplacement['sud'];
                echo '</div>'; //DIV DE LA MAP
                
                $map->getImageCssBack();

            ?>
                <div class="basdepage">
                </div>
                <?php
            }

    }else{
        echo $errorMessage;
    }
    ?>
    </div><!--fin centragePrincipal"-->
</body>
<?php include "ihm/jsDesPages/jsMap.php"; ?>
</html>