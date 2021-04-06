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
    <body class="bodyAccueil">
        <?php
        include "session.php";

        if($access === true){
            $access = $Joueur1->DeconnectToi();
        }
        if($access === true){
            ?>

            <?php
        }else{
            echo $errorMessage;
        }
        ?>
    </body>
</html>