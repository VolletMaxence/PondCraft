

<?php



$newPersonnage = new Personnage($mabase);



echo '<div class="testUnitaire"><p>Test 1 Lecture d\'un Personnage  </p>';
$newPersonnage->setPersonnageById(1);
$newPersonnage->getNom();
$newPersonnage->getBardeVie();
$newPersonnage->renderHTML();

echo "</div>";


echo '<div class="testUnitaire"><p>Test 2 Lecture d\'un Personnage de type Perso </p>';
$newPersonnage->setPersonnageById(412);
$newPersonnage->getNom();
$newPersonnage->getBardeVie();
$newPersonnage->renderHTML();

echo "</div>";

echo '<div class="testUnitaire"><p>Test 2 création d\'un Personnage de type Perso </p>';
$newPersonnage = $newPersonnage->CreatNewPersonnage(1);
if(!is_null($newPersonnage)){
    $newPersonnage->getBardeVie();
    $newPersonnage->renderHTML();
}



echo "</div>";



?>





