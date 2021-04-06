<?php
 echo '<div class="testUnitaire"><p>Test 1 Creation Arme </p>';

    $Arme = new Arme($mabase);
    $Arme = $Arme->createArmeAleatoire();


    echo 'Affichae de l arme ';
    $Arme->setEquipementByID($Arme->getId());
    echo "<p>le nom est : ".$Arme->getNom()." ";
    echo " le id est : ".$Arme->getId()." ";
    echo " la valeur est : ".$Arme->getValeur()." ";
    echo " le lvl  est : ".$Arme->getLvl()." ";
    echo " l'efficacite est : ".$Arme->getEfficacite()." ";
    echo " la categorie est : ".var_dump($Arme->getCategorie())." ";
    $type = $Arme->getType();
    echo "  <p>le type est : id ".$type['id']." / info :".$type['information']." / nom : ".$type['nom']." </p>" ;
    
    
    $perso = new Personnage($mabase);
    $perso->setEntiteById($idEntitePersonnage);
    echo '<p>Equipe un perso d attaqte '.$perso->getAttaque().'</p>';
    echo 'Ajout dans le sac ';
    $perso->addEquipement($Arme);
    echo 'Puis equipement ';
    $Arme->equipeEntite($perso);
    echo 'Affichage des nouvelle stat de l attaque :  ';
    echo $perso->getAttaque();

    
    $Arme->desequipeEntite($perso);
    $perso->removeEquipementById($Arme->getId());
    
    if($Arme->getId()>0){
        echo "<p>suppression de l'equipement".$Arme->deleteEquipement($Arme->getId())."</p>";
    }else{
        echo '<div style="color:red">la suppression a echoué car pas id</div>';
    }

    echo 'Affichage des nouvelle stat de l attaque :  ';
    echo $perso->getAttaque();

 echo '</div>';

?>