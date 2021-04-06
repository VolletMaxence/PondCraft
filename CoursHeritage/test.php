<?php echo "page de test CCOUCOU SN1";

include "Personnage.php";
include "Mage.php";
include "Guerrier.php";

$Perso1 = new Personnage("Bob");
$Perso1->renduHTML();

$Perso2 = new Personnage("Dylan");
$Perso2->renduHTML();


?>
    <p> Les Mages : </p>

<?php


$Mage3 = new Mage("Gandalf");
$Mage3->renduHTML();


?>
    <p> Les guerriers : </p>

<?php

$GuerrierLegendaire = new Guerrier("JeanClaude");
$GuerrierLegendaire->renduHTML();
$GuerrierLegendaire->boire();
?>