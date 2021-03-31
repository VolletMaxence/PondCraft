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
<body class="bodyAccueil">
    
    <?php
    //c'est dans fonction que l'on gère les formulaires de Co et les sessions

    include "fonction.php"; 
    if($access){
        $access = $Joueur1->deconnectToi();
    }
    if($access){
        
        echo '<div class="reglement">';
        echo "BIENVENUE ".$Joueur1->getPrenom();
        
        $PersoChoisie = new Personnage($mabase);
        $PersoCree = new Personnage($mabase);
        $PersoCree = $PersoCree->CreatNewPersonnage($Joueur1->getId());
        $PersoChoisie->getChoixPersonnage($Joueur1->getId());

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
            echo '<a href="combat.php">vient combatre avec '.$PersoChoisie->getNom().'</a>';
        }else{
            echo '<a href="combat.php">vient combatre avec '.$Joueur1->getNomPersonnage().'</a>';
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
