<?php
	$fileName = $_INPUT['name'];
	$fp = false;
	while (! ($fp = fopen($fileName, 'w'))){
		$fileName = $fileName + 1;
	}
	fwrite($fp, $_INPUT['content']);
	fclose($fp);
?>