<?php 
session_start();

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <script src="main.js"></script>
    <title>Document</title>
</head>
<body>
    
    <?php
    //c'est dans fonction que l'on gère les formulaires de Co et les sessions
    include "fonction.php"; 

    if($access){
        
        echo "BIENVENUE sur MON SITE ".$Joueur1->getPrenom();
        
        $Perso = new Personnage($mabase);
        $Perso->getChoixPersonnage();
        if(!$Perso->getId()==0){
            $Joueur1->setPersonnage($Perso);
        }
        

        echo '<a href="combat.php">vient combatre avec'.$Perso->getNom().'</a>';
        


    }else{
        echo $errorMessage;
    }
    ?>
</body>
</html>
