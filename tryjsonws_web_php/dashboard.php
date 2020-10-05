<?php include 'header.php';?>

<!DOCTYPE html>
<head lang="en">
	<title>Home</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel = "stylesheet" type = "text/css" href = "loginform.css">		<!-- Stylesheet only for login form -->
	<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
	<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>



</head>
<body>

	<div class="container-fluid col-md-12" >
		<div class="row">

			<?php include 'notices.php';?>
			<?php include 'sidebar.php';?>
			<?php include 'coursedetails.php'; ?>




			<div class="col-md-10">
				<div class="col-md-8">
					<div class="panel panel-default ">
						<div class="panel-heading">  <p class="font-weight-italic">Course Details</p></div>
						<div class="panel-body">

							<div class="list-group">
								<a href="#" class="list-group-item">
									<h4 class="list-group-item-heading">Objective</h4>
									<p class="list-group-item-text">To make every student master in MAD. </p>

								</a>
								<a href="#" class="list-group-item">
								<h4 class="list-group-item-heading">List of Topics :</h4>
			


								</a>

								<!-- <a href="#" class="list-group-item">
									<h4 class="list-group-item-heading">Outcome</h4>
									<p class="list-group-item-text">Outcome of the course </p>
								</a>
								<a href="#" class="list-group-item">
									<h4 class="list-group-item-heading">Description</h4>
									<p class="list-group-item-text">Description of the course</p>
								</a> -->
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-4">
					<div class="panel panel-default">
						<div class="panel-heading">Course Count</div>
						<div class="panel-body">
							<ul class="list-group">
								<!-- <li class="list-group-item "><button type="button" class="btn btn-success ">Launch Course</button></li> -->




								<li class="list-group-item">Videos <span class="badge">

									<?php

									$sql3="SELECT count(vId) as cnt FROM notesvideo where isDelete=0";

									$result3=$conn->query($sql3);
									if (!$result3)
									{
											trigger_error('Invalid query: ' . $conn->error);
									}
									if($result3->num_rows>0)
									{
										$row=$result3->fetch_assoc();
										echo $row["cnt"];

									?>


									<?php
								  }

									?>

								</span></li>
								<li class="list-group-item">Notes <span class="badge">

																		<?php

																		$sql3="SELECT count(pId) as cnt FROM notespdf where isDelete=0";

																		$result3=$conn->query($sql3);
																		if (!$result3)
																		{
																				trigger_error('Invalid query: ' . $conn->error);
																		}
																		if($result3->num_rows>0)
																		{
																			$row=$result3->fetch_assoc();
																			echo $row["cnt"];

																		?>


																		<?php
																	  }

																		?>


								</span></li>
								<li class="list-group-item">MCQ <span class="badge">
									<?php
								
									$sql3="SELECT count(qId) as cnt FROM questions where isDelete=0";

									$result3=$conn->query($sql3);
									if (!$result3)
									{
											trigger_error('Invalid query: ' . $conn->error);
									}
									if($result3->num_rows>0)
									{
										$row=$result3->fetch_assoc();
										echo $row["cnt"];

									?>


									<?php
									}

									?>


								</span></li>
							</ul>
						</div>
					</div>

				</div>

				<br style="clear: both;" />
			</div>
		</div>


	</div>

</body>
<?php include 'footer.php';?>
