$(function() {
	var timeout = 500;
	var closetimer = 0;
	var ddmenuitem = 0;

	//hideSidebar();
	setTabbedForm();
	setTabBinding();
	setDatePicker();
	checkProject();
	document.onclick = jsddm_close;

	// START menubar functions
	$('ul#top_menu>li').bind('mouseover', jsddm_open);
	$('ul#top_menu>li').bind('mouseout', jsddm_timer);
	
	$('table#top_menu>td').bind('mouseover', jsddm_open);
	$('table#top_menu>td').bind('mouseout', jsddm_timer);
	// END menubar function
	
	setModal();
	
	$('ul#top_menu a').click(function() {
		var wrapper = $('#main'),
			current = $('#main>.ajax'),
			currentH = current.height();
		
		$.ajax({
			url: $(this).attr('href'),
			beforeSend: function() {
				wrapper.css('height', currentH);
				current.fadeOut(250);
			},
			error: function(xhr, msg) {
				setTimeout(function() {
					$('#wrapper>#main>.ajax').html('<h2>'+msg+'</h2>');
				}, 250);
			},
			success: function(data) {
				var open = $(data).find('div.ajax');
				
				setTimeout(function() {
					$('#main>.ajax').html(open.html());
					setModal();
					setDatePicker();
				}, 250);
			},
			complete: function() {
				setTimeout(function() {
					wrapper.animate({
						height: $('div.ajax').height()
					}, 250, function() {
						current.fadeIn(250);
						wrapper.removeAttr('style');
					});
				}, 500);
			}
		});
		return false;
	});

	function jsddm_open() {
		jsddm_canceltimer();
		jsddm_close();
		ddmenuitem = $(this).find('ul').eq(0).css('display', 'block');
	}

	function jsddm_close() {
		if (ddmenuitem)
			ddmenuitem.css('display', 'none');
	}

	function jsddm_timer() {
		closetimer = window.setTimeout(jsddm_close, timeout);
	}

	function jsddm_canceltimer() {
		if (closetimer) {
			window.clearTimeout(closetimer);
			closetimer = null;
		}
	}
	
	function hideSidebar() {
		$('#sidebar').css('display','none');
	}

	// START fancybox functions
	function setModal() {
		$('.modal').fancybox({
			'hideOnOverlayClick': false,
			'scrolling': 'no',
			'showCloseButton': false,
			'type': 'ajax',
			'ajax': {
				dataFilter: function(data) {
					return $(data).find('#main>.ajax');
				}
			},
			'onComplete': function() {
				var fc = $('#fancybox-content');
					
				if (fc.find('form#add_user') != null)
					validateAddUser();
				if (fc.find('form#edit_user') != null)
					validateEditUser();
				if (fc.find('form#univ') != null)
					validateUniv();
				if (fc.find('form#status') != null)
					validateStatus();
				if (fc.find('form#form_country') != null)
					validateCountry();
				if (fc.find('form#form_location') != null)
					validateLocation();
				if (fc.find('form#position') != null)
					validatePosition();
				if (fc.find('form#project') != null)
					validateProject();
				if (fc.find('form#skill') != null)
					validateSkill();
				
				if (fc.find('form#add_staff') != null) {
					validateStaff();
					setTabbedForm();
					setTabBinding();
					setDatePicker();
					checkProject();
				}
			}
		});
	}

	// set default active tab in staff form
	function setTabbedForm() {
		$('div[id!=personal].info').addClass('tab_hide').css('display','none');
	}

	function setTabBinding() {
		$('ul.tabs a').click(function() {
			var linkId = $(this).attr('href'),
				form = $(this).parents('.ajax'),
				wrapper = form.find('#info_container'),
				current = form.find('div.info:not(:hidden)'),
				currenth = current.height(),
				open = form.find('div#' + linkId + '.info'),
				openh = open.height();
			
			wrapper.css('height', currenth+16);
			current.fadeOut(500, function() {
				wrapper.animate({
						height:openh+16
					}, 500, function() {
						open.fadeIn(500, function() {
							wrapper.removeAttr('style');
						}).removeClass('tab_hide');
				});
			}).addClass('tab_hide');
		});
	}
	
	// form add user
	function validateAddUser() {
		$('form#add_user').validate({
			rules: {
				name: {
					required: true,
					maxlength: 50,
					minlength: 3
				},
				email: {
					required: true,
					email: true
				},
				password: {
					required: true,
					minlength: 3
				},
				password2: {
					required: true,
					equalTo: "#password"
				}
			}
		});
	}
	
	// form edit user
	function validateEditUser() {
		$('form#edit_user').validate({
			rules: {
				name: {
					required: true,
					maxlength: 50,
					minlength: 3
				},
				email: {
					required: true,
					email: true
				},
				password: {
					minlength: 3
				},
				password2: {
					required: '#password:filled',
					equalTo: '#password'
				}
			}
		});
	}
	
	// validate univ
	function validateUniv() {
		$('form#univ').validate({
			rules: {
				univName: { // sama dengan field di jsp
					required: true,
					maxlength: 50,
					minlength: 3
				},
				univAddress: {
					required: true,
					maxlength: 200,
					minlength: 5
				}
			},
			message : {
				univName: { // sama dengan field di jsp
					required: "This field is required.",
					maxlength: "Please enter max 50 characters.",
					minlength: "Please enter at least 3 characters."
				},
				univAddress: {
					required: "This field is required.",
					maxlength: "Please enter max 50 characters.",
					minlength: "Please enter at least 5 characters."
				}

			}

		});
	}
	
	// validate status
	function validateStatus() {
		$('form#status').validate({
			rules: {
				statusName: {
					required: true,
					maxlength: 20,
					minlength: 3
				}
			},
			message : {
				statusName: { // sama dengan field di jsp
					required: "This field is required.",
					maxlength: "Please enter max 20 characters.",
					minlength: "Please enter at least 3 characters."
				}

			}
		});
	}
	
	// form country
	function validateCountry() {
		$('form#form_country').validate({
			rules: {
				name: {
					required: true,
					maxlength: 30,
					minlength: 3
				}
			}
		});
	}
	
	// VALIDATE LOCATION FORM
	function validateLocation() {
		$('form#form_location').validate({
			rules: {
				locName: {
					required: true,
					maxlength: 30,
					minlength: 3
				}
			}
		});
	}
	
	// VALIDATE POSITION FORM
	function validatePosition() {
		$('form#position').validate({
			rules: {
				posName: {
					required: true,
					maxlength: 20				
				},
				posDesc: {
					maxlength: 150					
				}
			}
		});
	}
	
	// VALIDATE PROJECT FORM
	function validateProject() {
		$('form#project').validate({
			rules: {
				name: {
					required: true,
					maxlength: 50				
				}
			}
		});
	}
	
	// VALIDATE SKILL FORM
	function validateSkill() {
		$('form#skill').validate({
			rules: {
				sklName: {
					required: true,
					maxlength: 30				
				}
			}
		});
	}

	// VALIDATE STAFF FORM
	function validateStaff() {
		$('form#staff').validate({
			rules: {
				'staffBean.staffId': {
					required: true,
					maxlength: 10				
				},
				'staffBean.fullName': {
					required:true,
					maxlength: 30			
				},
				'staffBean.birthDate': {
					required: true,					
					date: true
				},
				'staffBean.homeAddress': {
					required: true,
					maxlength: 200				
				},
				'staffBean.currentAddress': {
					required: true,
					maxlength: 200				
				},
				'staffBean.phoneNumber': {
					maxlength: 15,
					number: true
				},
				'staffBean.mobileNumber': {
					required: true,
					maxlength: 15,
					number: true
				},
				'batchNo': {
					required: true,
					maxlength: 11,
					number: true
				},
				'staffBean.joinDate': {
					required: true,					
					date: true
				},
				'staffBean.skills[].skillId': {
					required: true
				}
			},
			messages: {
				'staffBean.staffId': {
					required: 'Please enter staff ID',
					maxlength: 'Please enter no more than 10 character'				
				},
				'staffBean.fullName': {
					required:'Please enter staff\'s name',
					maxlength: 'Please enter no more than 30 characters'			
				},
				'staffBean.birthDate': {
					required: 'Birth date is required',					
					date: 'Please enter a valid date'
				},
				'staffBean.homeAddress': {
					required: 'Please enter home address',
					maxlength: 'Please enter no more than 200 characters'				
				},
				'staffBean.currentAddress': {
					required: 'Please enter current address',
					maxlength: 'Please enter no more than 200 characters'				
				},
				'staffBean.phoneNumber': {
					maxlength: 'Please enter no more than 15 characters',
					number: 'Please enter number only'
				},
				'staffBean.mobileNumber': {
					required: 'Mobile phone number is required',
					maxlength: 'Please enter no more than 15 characters',
					number: 'Please enter number only'
				},
				'batchNo': {
					number: 'Please enter number only'
				},
				'staffBean.joinDate': {
					required: 'Join date is required',					
					date: 'Please enter a valid date'
				},
				'staffBean.skills[].skillId': {
					required: 'pilihlah'
				}
			}
		});
	}
	
	// SET DATE PICKER
	function setDatePicker() {
	    $('input#birth').DatePicker({
	        date: '2000-01-01',
	        current: '2000-01-01',
	        starts: 1,
	        position: 'right',
	        view: 'years',
	        onChange: function(formated, dates){
	            $('input#birth').val(formated);
	        }
	    });
	    
	    $('input#join').DatePicker({
	        date: '2000-01-01',
	        current: '2000-01-01',
	        starts: 1,
	        position: 'right',
	        view: 'years',
	        onChange: function(formated, dates){
	            $('input#join').val(formated);
	        }
	    });
	    
	    $('input#resignDate').DatePicker({
	        date: '2000-01-01',
	        current: '2000-01-01',
	        starts: 1,
	        position: 'right',
	        view: 'years',
	        onChange: function(formated, dates){
	            $('input#resignDate').val(formated);
	        }
	    });

	    $('#filter_box input#start').DatePicker({
	        date: '2000-01-01',
	        current: '2000-01-01',
	        starts: 1,
	        position: 'right',
	        view: 'years',
	        onChange: function(formated, dates){
	            $('#filter_box input#start').val(formated);
	        }
	    });
	    
	    $('#filter_box input#end').DatePicker({
	        date: '2000-01-01',
	        current: '2000-01-01',
	        starts: 1,
	        position: 'right',
	        view: 'years',
	        onChange: function(formated, dates){
	            $('#filter_box input#end').val(formated);
	        }
	    });
	}
	
	// DISABLE PROJECT START AND END DATE BY DEFAULT
	function checkProject() {
		var chkPrj = $('.chk_prj'),
			inital = chkPrj.is(':checked'),
			txt = chkPrj.parents('tr').find('.input_text').attr('disabled', !inital);
				
		chkPrj.click(function() {
			$(this).parents('tr').find('.input_text').val('').attr('disabled', !this.checked);
		});
	}
});