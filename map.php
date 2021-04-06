<?php
session_start();
?>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <!-- Compatible / UTF / Viewport-->
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Style CSS / Script -->
            <link rel="stylesheet" href="css/style.css">
            <link rel="stylesheet" href="css/map.css">
            <link rel="stylesheet" href="css/perso.css">
            <link rel="stylesheet" href="css/item.css">
            <link rel="stylesheet" href="css/entite.css">
            <script src="main.js"></script>
        <!-- Informations Générales -->
            <title>Projet Full Stack - Combat</title>
            <meta name='description' content='Projet Full Stack - Combat'>
            <link rel='shortcut icon' href='favicon.ico'>
            <meta name='author' content='La Providence - Amiens'>
        <!-- Intégration Facebook -->
            <meta property='og:title' content='Projet Full Stack - Combat'>
            <meta property='og:description' content='Projet Full Stack - Combat'>
            <meta property='og:image' content='favicon.ico'>
        <!-- Intégration Twitter -->
            <meta name='twitter:title' content='Projet Full Stack - Combat'>
            <meta name='twitter:description' content='Projet Full Stack - Combat'>
            <meta name='twitter:image' content='favicon.ico'>
    </head>
    <body>
        <div class="centragePrincipal">
            <?php
                include "session.php";

                // Vérifie que la Session est Valide avec le bon Mot de Passe.
                if($access === true){
                    $access = $Joueur1->DeconnectToi();
                }
                // Vérifie qu'il ne s'est pas déconnecté.
                if($access === true){
                    //gestion accès map:
                    $Personnage = $Joueur1->getPersonnage();
                    if(is_null($Personnage->getId())){
                        ?> 
                            <p>Il faut créer un personnage d'abord.</p>
                            <p><a href="index.php">Retour à l'origine du tout</a></p>
                        <?php
                    }else{
                        ?>
                            <p><a href="index.php">Retour à l'origine du tout</a></p>
                            <div>
                                <?php
                                    // Quand on ne génère pas de nouvelle position ou que aucune position
                                    // n'est renseignée, on peut appeler un autre personnage.
                                    if(!(isset($_GET["position"]) && $_GET["position"]==='Generate')){
                                        ?>
                                            <p>Tu peux appeler un autre personnage.</p>
                                        <?php
                                        $Personnage->getChoixPersonnage($Joueur1);
                                        $Joueur1->setPersonnage($Personnage);
                                    }
                                    //AFFICHAGE de l'entete d'un hero
                                    include "ihm/affichagePersoEtSac.php";
                                    //AFFICHAGE d'UN TOOLTIP
                                    include "ihm/affichageTooltip.php";
                                    //CHARGEMENT  DE LA MAP
                                    include "ihm/chargementDeLaMap.php";
                                    //HTML  DE LA MAP
                                ?>
                                <div class="lamap">
                                    <?= $BousoleDeplacement['nord'] ?>
                                    <div class="mapOuest">
                                        <?= $BousoleDeplacement['ouest'] ?>
                                        <div class="mapEst">
                                            <div class="mapCentre">
                                                <?php $Joueur1->getVisitesHTML(6) ?>
                                                <div class="infoMap">
                                                    <?= $map->getInfoMap() ?>
                                                </div>
                                                <?php
                                                    //affichage des autres joueurs sur la carte
                                                    include "ihm/affichageAutrePersos.php";
                                                    //affiche les mob enemie et capturé;
                                                    include "ihm/affichageItemsMap.php";
                                                    //AFFICHAGE DES ITEMS DE LA MAP
                                                    include "ihm/affichageTousLesMobs.php";
                                                    //AFFICHAGE DES EQUIPEMENT DE LA MAP
                                                    include "ihm/affichageEquipementsMap.php";
                                                ?>
                                            </div>
                                            <?= $BousoleDeplacement['est'] ?>
                                        </div>
                                    </div>
                                    <?= $BousoleDeplacement['sud'] ?>
                                </div>
                                <?php $map->getImageCssBack() ?>
                                <div class="basdepage"></div>
                            </div>
                        <?php
                    }
                }else{
                    echo $errorMessage;
                }
            ?>
        </div>
    </body>
    <?php include "ihm/jsDesPages/jsMap.php" ?>
</html>