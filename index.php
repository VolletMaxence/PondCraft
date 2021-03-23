<?php include "fonction.php"; ?>
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
    <?

    if(!is_null($mabase)){
        echo "Connecte Covid";
    }else{
        echo "Vous n'avez pas les bases";
    }
    
         

    ?>
</body>
</html>
