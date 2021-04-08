<?php
    if(rand(0,1)==1){

        echo '<div class="letooltip">';
            $tooltip = new Tooltip($mabase);
            echo $tooltip->getTooltipAleatoire();
        echo '</div>';
    }
    

?>