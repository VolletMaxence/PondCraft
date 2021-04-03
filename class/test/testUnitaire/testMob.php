

<?php



$newMob = new Mob($mabase);
$map = New Map($mabase);
$map->setMapByID(1);
if(is_null($map)){
    echo "<h1>map non chargé</h1>";
}



echo '<div class="testUnitaire"><p>Test 1 Lecture d\'un Mob  </p>';
$newMob->setMobById(460);
$newMob->getNom();
$newMob->getBardeVie();
$newMob->renderHTML();

echo "</div>";

echo '<div class="testUnitaire"><p>Test 2 création d\'un Mob Aléatoire </p>';


$newMob = $newMob->CreateMobAleatoire($map);
if(!is_null($newMob)){
    $newMob->getBardeVie();
    $newMob->renderHTML();
}



echo "</div>";



?>





