<?php
    require_once('config.inc.php');
	

    $pdf_fileid = $_POST['substance_id'];

    $sql = '(SELECT filename FROM oro_attachment_file WHERE id = 
            (SELECT document_id FROM chem_scan_safety_datasheet WHERE hs_id = 
                (SELECT hs_id FROM chem_scan_hs_substance WHERE substance_id = :pdf_fileid )))';
                	//(SELECT id FROM chem_scan_substance WHERE name LIKE "oxytocin"))))';


    $statement = $connection->prepare($sql);
    $statement->bindParam(':pdf_fileid', $pdf_fileid, PDO::PARAM_STR);
    $statement->execute();
    $query_result = $statement->fetchColumn();
    
    $pdf_directory = "http://141.45.92.216/";
    $pdf_file = $pdf_directory . $query_result;
    echo $pdf_file;

?>