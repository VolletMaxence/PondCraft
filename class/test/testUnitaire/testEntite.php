

<?php



$newEntite = new Entite($mabase);



echo "<div><p>Test 1 Lecture d'un Entite de type Perso </p>";
$newEntite->setEntiteById(1);
$newEntite->getNom();
$newEntite->getBardeVie();
$newEntite->renderHTML();

echo "</div>";


echo "<div><p>Test 2 Lecture d'un Entite de type Perso </p>";
$newEntite->setEntiteById(412);
$newEntite->getNom();
$newEntite->getBardeVie();
$newEntite->renderHTML();

echo "</div>";





?>





