<?php
 echo '<div class="testUnitaire"><p>Test 1 Creation Armure </p>';

    $Armure = new Armure($mabase);
    $Armure = $Armure->createArmureAleatoire();


    echo 'Affichae de l armure ';
    $Armure->setEquipementByID($Armure->getId());
    echo "<p>le nom est : ".$Armure->getNom()." ";
    echo " le id est : ".$Armure->getId()." ";
    echo " la valeur est : ".$Armure->getValeur()." ";
    echo " le lvl  est : ".$Armure->getLvl()." ";
    echo " l'efficacite est : ".$Armure->getEfficacite()." ";
    echo " la categorie est : ".var_dump($Armure->getCategorie())." ";
    $type = $Armure->getType();
    echo "  <p>le type est : id ".$type['id']." / info :".$type['information']." / nom : ".$type['nom']." </p>" ;
    
    
    $perso = new Personnage($mabase);
    $perso->setEntiteById($idEntitePersonnage);
    echo '<p>Equipe '.$perso->getNom().' de defence  '.$perso->getDefense().'</p>';
    echo 'Ajout dans le sac ';
    $perso->addEquipement($Armure);
    echo 'Puis equipement ';
    $Armure->equipeEntite($perso);
    echo 'Affichage des nouvelle stat de la defense :  ';
    echo $perso->getDefense();
    $perso->renderHTML();

    
    $Armure->desequipeEntite($perso);
    $perso->removeEquipementById($Armure->getId());
    
    if($Armure->getId()>0){
        echo "<p>suppression de l'equipement".$Armure->deleteEquipement($Armure->getId())."</p>";
    }else{
        echo '<div style="color:red">la suppression a echoué car pas id</div>';
    }

    echo 'Affichage des nouvelle stat de la defence :  ';
    echo $perso->getDefense();

 echo '</div>';

?>