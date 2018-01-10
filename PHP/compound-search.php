<?php

     if(isset($_POST['searchQuery']))
     {
     	  require_once('config.inc.php');
	  $search_query='%' . $_POST['searchQuery'] . '%';
         
     
         $sql = "SELECT * FROM chem_scan_substance WHERE name LIKE :search_Query";
         /*
         $sql = '(SELECT filename FROM oro_attachment_file WHERE id = 
            (SELECT document_id FROM chem_scan_safety_datasheet WHERE hs_id = 
                (SELECT hs_id FROM chem_scan_hs_substance WHERE substance_id = 
                    (SELECT id FROM `chem_scan_substance` 
                    WHERE name LIKE :search_Query
                    OR cas LIKE :search_query
                    OR eg LIKE :search_query))))';
         
            $sql2 = 'SELECT name FROM chem_scan_substance WHERE name LIKE :search_query
                OR cas LIKE :search_query
                OR eg LIKE :search_query'; */
         
      $statement = $connection->prepare($sql);
	  $statement->bindParam(':search_query', $search_query, PDO::PARAM_STR);
          $statement->execute();
          if($statement->rowCount())
          {
	    $row_all = $statement->fetchall(PDO::FETCH_ASSOC);
	    header('Content-type: application/json');
   	    echo json_encode($row_all);
          }  
          elseif(!$statement->rowCount())
          {
	    echo "no rows";
          }
     }
		  
?>