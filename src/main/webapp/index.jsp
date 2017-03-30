<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<title>Activities of Daily Life</title>
<!-- Meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Responsive HTML5 Website Landing Page for Developers">
<meta name="author" content="Xiaoying Riley at 3rd Wave Media">
<link rel="shortcut icon" href="favicon.ico">
<!-- Global CSS -->
<link rel="stylesheet"
	href="assets/plugins/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet"
	href="assets/plugins/bootstrap/css/animate.css">
<!-- Plugins CSS -->
<link rel="stylesheet"
	href="assets/plugins/font-awesome/css/font-awesome.css">

<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/octicons/2.0.2/octicons.min.css">

<!-- Theme CSS -->
<link id="theme-style" rel="stylesheet" href="assets/css/styles.css">

<link
	href="assets/plugins/bootstrap/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">

</head>

<body>
	<!-- ******HEADER****** -->
	<header class="header">
		<div class="container">
			<img class="profile-image img-responsive pull-left"
				src="assets/images/profile.png" alt="Ken" />
			<div class="profile-content pull-left">
				<h1 class="name">Ken</h1>
				<h2 class="desc">Treatment for Depression Patient</h2>
				<ul class="social list-inline">
					<li><a href="#"><i class="fa fa-twitter"></i></a></li>
					<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
					<li><a href="#"><i class="fa fa-linkedin"></i></a></li>
					<li><a href="#"><i class="fa fa-github-alt"></i></a></li>
					<li class="last-item"><a href="#"><i
							class="fa fa-hacker-news"></i></a></li>
				</ul>
			</div>
			<!--//profile-->
			<a class="btn btn-cta-primary pull-right"
				href="http://yaochenkun.cn/" target="_blank"><i
				class="fa fa-paper-plane"></i> Contact Me</a>
		</div>
		<!--//container-->
	</header>
	<!--//header-->

	<div class="container sections-wrapper">
		<div class="row">
			<div class="primary col-md-12 col-sm-12 col-xs-12">
				<section class="about section">
					<div class="section-inner">
						<h2 class="heading" style="margin-bottom: 10px;">Activities
							of Daily Life</h2>
						<div class="content">
							<p>This is a service aimed at depression patients, which is
								used to monitor the behaviour of the patients, show their recent
								performance of activities and finally help them become better.</p>
						</div>
						<h2 class="heading" style="margin-bottom: 10px;">How to use?</h2>
						<div class="content">
							<p>
								1 Select <b style="color: #79d19a;">one day</b> in calendar.
							</p>
							<p>
								2 Click the <b style="color: #79d19a;">blue button</b> below to
								confirm.
							</p>
							<p>
								3 The service will <b style="color: #79d19a;">diagnose</b> the
								performance of the patient's activities in this day.
							</p>
							<p>
								4 Check results on <b style="color: #79d19a;">'Report' items</b>
								below.
							</p>
						</div>
						<h2 class="heading" style="margin-bottom: 10px;">Meaning of
							Icons</h2>
						<div class="content">
							<p>
								<span class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;means
								a <b style="color: #79d19a;">wrapped plan</b>
							</p>
							<p>
								<span class="glyphicon glyphicon-tag"></span>&nbsp;&nbsp;means
								an <b style="color: #79d19a;">atomic action</b>
							</p>
							<p>
								<span class="glyphicon glyphicon-ok"></span>&nbsp;&nbsp;means
								the plan/action is <b style="color: #79d19a;">completed</b>
							</p>
							<p>
								<span class="glyphicon glyphicon-repeat"></span>&nbsp;&nbsp;means
								the plan/action is <b style="color: #79d19a;">activated</b>
							</p>
							<p>
								<span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;means
								the plan/action is <b style="color: #79d19a;">considered</b>
							</p>
						</div>
						<!--//content-->
					</div>
					<!--//section-inner-->
				</section>
				<!--//section-->
				<section class="latest section">
					<div class="section-inner">
						<h2 class="heading">Calendar</h2>

						<div class="content">

							<div class="item featured text-center">
								<h3 class="title"></h3>

								<div class="control-group">
									<div class="controls input-append date form_date" data-date=""
										data-date-format="dd MM yyyy" data-link-field="dtp_input2"
										data-link-format="yyyy-mm-dd">
										<input name="beginDate" size="16" type="text" value=""
											readonly> <span class="add-on"><i
											class="icon-remove"></i></span> <span class="add-on"><i
											class="icon-th"></i></span>
									</div>
									<input type="hidden" id="dtp_input2" value="" /><br />
								</div>


								<!--//desc-->
								<a class="btn btn-cta-secondary" href="javascript:void(0)"
									target="_blank" onclick="diagnose();">diagnose & report</a>
							</div>
							<!--//item-->
							<hr class="divider" />
							<h2 class="heading">Report</h2>

							<div class="item row">
								<div class="desc col-md-12 col-sm-12 col-xs-12">
									<h3 class="title">
										<a
											href="http://themes.3rdwavemedia.com/website-templates/responsive-bootstrap-theme-mobile-apps-atom/"
											target="_blank">Plan1 - Morning</a>
									</h3>

									<div class="featured-image">
										<a
											href="http://themes.3rdwavemedia.com/website-templates/responsive-bootstrap-theme-for-products-velocity/"
											target="_blank"> <img
											class="img-responsive project-image"
											src="assets/images/morning_img.png" alt="project name"
											style="margin: 0 auto;">
										</a>
									</div>

									<br>

									<div id="chart_div_morning" style="display: none;">
										<div id="chart_morning_legend" class="chart-legend"></div>
										<canvas id="chart_morning" width="1100" height="400"></canvas>
										<blockquote class="quote"
											style="border-left: 5px solid #79d19a">
											<span class="title" style="color: #778492;">Final Score:</span> <span
												class="title" style="color: #79d19a;"><strong
												id="score_morning"></strong></span> <br> <br> <span
												class="title" style="color: #778492;">Final Achievement:</span> <span
												class="title" style="color: #79d19a;"><strong
												id="achievement_morning"></strong></span> <br>
										</blockquote>
										<blockquote class="quote"
											style="border-left: 5px solid #79d19a">

											<table id="table_morning" class="table table-condensed"
												style="color: #778492;">
												<thead>
													<tr>
														<th>#</th>
														<th>Name</th>
														<th>Type</th>
														<th>Your Score</th>
														<th>Full Score</th>
														<th>Achievement</th>
														<th>Finished Time</th>
													</tr>
												</thead>
												<tbody></tbody>
											</table>
										</blockquote>
									</div>
								</div>
								<!--//desc-->
							</div>

							<hr class="divider" />
							<!--//item-->
							<div class="item row">
								<div class="desc col-md-12 col-sm-12 col-xs-12">
									<h3 class="title">
										<a
											href="http://themes.3rdwavemedia.com/website-templates/responsive-bootstrap-theme-mobile-apps-atom/"
											target="_blank">Plan2 - Afternoon</a>
									</h3>
									<div id="chart_div_afternoon" style="display: none;">
										<canvas id="chart_afternoon" width="1100" height="400"></canvas>
										<blockquote class="quote"
											style="border-left: 5px solid #79d19a">
											<span class="title" style="color: #778492;">Final Score:</span> <span
												class="title" style="color: #79d19a;"><strong
												id="score_afternoon"></strong></span> <br> <br> <span
												class="title" style="color: #778492;">Final Achievement:</span> <span
												class="title" style="color: #79d19a;"><strong
												id="achievement_afternoon"></strong></span> <br>
										</blockquote>
										<blockquote class="quote"
											style="border-left: 5px solid #79d19a">

											<table id="table_afternoon" class="table table-condensed"
												style="color: #778492;">
												<thead>
													<tr>
														<th>#</th>
														<th>Name</th>
														<th>Type</th>
														<th>Your Score</th>
														<th>Full Score</th>
														<th>Achievement</th>
														<th>Finished Time</th>
													</tr>
												</thead>
												<tbody></tbody>
											</table>
										</blockquote>
									</div>
								</div>
								<!--//desc-->
							</div>
							<!--//item-->
							<hr class="divider" />
							<div class="item row">
								<div class="desc col-md-12 col-sm-12 col-xs-12">
									<h3 class="title">
										<a
											href="http://themes.3rdwavemedia.com/website-templates/responsive-bootstrap-theme-mobile-apps-atom/"
											target="_blank">Plan3 - Evening</a>
									</h3>
									<div id="chart_div_evening" style="display: none;">
										<canvas id="chart_evening" width="1100" height="400"></canvas>
										<blockquote class="quote"
											style="border-left: 5px solid #79d19a">
											<span class="title" style="color: #778492;">Final Score:</span> <span
												class="title" style="color: #79d19a;"><strong
												id="score_evening"></strong></span> <br> <br> <span
												class="title" style="color: #778492;">Final Achievement:</span> <span
												class="title" style="color: #79d19a;"><strong
												id="achievement_evening"></strong></span> <br>
										</blockquote>
										<blockquote class="quote"
											style="border-left: 5px solid #79d19a">

											<table id="table_evening" class="table table-condensed">
												<thead>
													<tr>
														<th>#</th>
														<th>Name</th>
														<th>Type</th>
														<th>Your Score</th>
														<th>Full Score</th>
														<th>Achievement</th>
														<th>Finished Time</th>
													</tr>
												</thead>
												<tbody></tbody>
											</table>
										</blockquote>
									</div>
								</div>
							</div>




							<!--//item-->

						</div>
						<!--//content-->
					</div>
					<!--//section-inner-->
				</section>
				<!--//section-->
			</div>
			<!--//primary-->

		</div>
		<!--//row-->
	</div>
	<!--//masonry-->

	<!-- ******FOOTER****** -->
	<footer class="footer">
		<div class="container text-center">
			<!--/* This template is released under the Creative Commons Attribution 3.0 License. Please keep the attribution link below when using for your own project. Thank you for your support. :) If you'd like to use the template without the attribution, you can check out other license options via our website: themes.3rdwavemedia.com */-->
		</div>
		<!--//container-->
	</footer>
	<!--//footer-->

	<!-- Javascript -->
	<script type="text/javascript"
		src="assets/plugins/jquery-1.11.3.min.js"></script>
	<script type="text/javascript"
		src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="assets/plugins/jquery-rss/dist/jquery.rss.min.js"></script>
	<!-- custom js -->
	<script type="text/javascript" src="assets/js/main.js"></script>

	<script type="text/javascript"
		src="assets/plugins/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript"
		src="assets/plugins/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript"
		src="assets/plugins/bootstrap/js/bootstrap-notify.min.js"></script>

	<script type="text/javascript" src="assets/js/Chart.js"></script>
	<script type="text/javascript" src="logic.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {

			//init calendar 
			$('.form_date').datetimepicker({
				language : 'fr',
				weekStart : 1,
				todayBtn : 1,
				autoclose : 1,
				todayHighlight : 1,
				startView : 2,
				minView : 2,
				forceParse : 0,
				format : 'yyyy-mm-dd'
			});
		});
	</script>

</body>
</html>
