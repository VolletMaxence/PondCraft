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
    <link rel="stylesheet" href="css/index.css">
    <script src="main.js"></script>
    <title>Document</title>
</head>
<body class="bodyAccueil">
    <?php
    //c'est dans fonction que l'on gère les formulaires de Co et les sessions

    include "session.php";
    if($access){
        $access = $Joueur1->DeconnectToi();
    }
    if($access){
        ?>
            <div class="reglement">
                <p>BIENVENUE <?= $Joueur1->getPrenom() ?> </p>
        <?php
        $PersoChoisie = new Personnage($mabase);
        $PersoCree = new Personnage($mabase);
        $PersoCree = $PersoCree->CreatNewPersonnage($Joueur1->getId());
        $PersoChoisie->getChoixPersonnage($Joueur1);

        if(!is_null($PersoCree)){
            $PersoChoisie = $PersoCree;
        }
        if(!$PersoChoisie->getId()==0){
            $Joueur1->setPersonnage($PersoChoisie);
        }
        ?>
                <div class="Action">
        <?php
        if(!empty($PersoChoisie->getNom())){
        ?>
                    <p> <a href="combat.php">Viens combattre avec <?= $PersoChoisie->getNom() ?> </a> </p>
        <?php
        }else{
        ?>
                    <p> <a href="combat.php">Viens combattre avec <?= $Joueur1->getNomPersonnage() ?> </a> </p>
        <?php
        }
        ?>
                </div>
            </div><!-- div reglement-->
        <?php
    }else{
        echo $errorMessage;
    }
    ?>
</body>
</html>