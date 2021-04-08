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
            <link rel="stylesheet" href="css/admin.css">
            <link rel="stylesheet" href="css/style.css">
            <link rel="stylesheet" href="css/index.css">
            <script src="main.js"></script>
        <!-- Informations Générales -->
            <title>Projet Full Stack - Panel Admins</title>
            <meta name='description' content='Projet Full Stack - Panel Admin'>
            <meta name='author' content='La Providence - Amiens'>
            <link rel='shortcut icon' href='favicon.ico'>
        <!-- Intégration Facebook -->
            <meta property='og:title' content='Projet Full Stack - Panel Admin'>
            <meta property='og:description' content='Projet Full Stack - Panel Admin'>
            <meta property='og:image' content='favicon.ico'>
        <!-- Intégration Twitter -->
            <meta name='twitter:title' content='Projet Full Stack - Panel Admin'>
            <meta name='twitter:description' content='Projet Full Stack - Panel Admin'>
            <meta name='twitter:image' content='favicon.ico'>
    </head>
    <body class="admin-panel">
        <?php
            include "session.php";

            // Vérifie que la Session est Valide avec le bon Mot de Passe.
            if($access === true){
                $access = $Joueur1->DeconnectToi();
            }
            // Vérifie qu'il ne s'est pas déconnecté.
            if($access === true){
                if($Joueur1->isAdmin() == true){
                    ?>
                        <div class='Div1 BG_Blanc'>
                            <h1 class='TITRE'>Panel Administrateur</h2>
                        </div>
                        <div class='Div1 BG_Rouge'>
                            <h3 class='TC'>Modification Personnages</h3>
                            <!-- Truc pour Modifier les Personnages -->
                        </div>
                        <div class='Div1 BG_Bleu'>
                            <h3 class='TC'>Modification Monstres</h3>
                            <!-- Truc pour Modifier les Monstres -->
                        </div>
                        <div class='Div1 BG_Jaune'>
                            <h3 class='TC'>Modification Map</h3>
                            <!-- Truc pour Modifier la Map -->
                        </div>
                        <div class='Div1 BG_Vert'>
                            <h3 class='TC'>Modification Items</h3>
                            <!-- Truc pour Modifier les Items -->
                        </div>
                    <?php
                }else{
                    ?>
                        <div class='Div1'>
                            <h2 class='TC'>Vous n'êtes pas Administrateur. Cette page ne vous est pas accessible.</h2>
                            <h3 class='TC'><a href='index.php'>Merci de retourner à la page principal.</a></h3>
                            <div><iframe width="560" height="315" src="https://www.youtube.com/embed/rTgj1HxmUbg?autoplay=1" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe></div>
                        </div>
                    <?php
                }
            }else{
                echo $errorMessage;
            }
        ?>
    </body>
</html>