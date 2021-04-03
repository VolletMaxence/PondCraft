

<?php



$newMob = new Mob($mabase);
$map = New Map($mabase);
$map->setMapByID(1660);



echo "<div><p>Test 1 Lecture d'un Mob  </p>";
$newMob->setMobById(1);
$newMob->getNom();
$newMob->getBardeVie();
$newMob->renderHTML();

echo "</div>";


echo "<div><p>Test 2 Lecture d'un Mob de type Perso </p>";
$newMob->setMobById(412);
$newMob->getNom();
$newMob->getBardeVie();
$newMob->renderHTML();

echo "</div>";

echo "<div><p>Test 2 création d'un Mob de type Perso </p>";


$newMob = $newMob->CreateMobAleatoire($map);
if(!is_null($newMob)){
    $newMob->getBardeVie();
    $newMob->renderHTML();
}



echo "</div>";



?>





