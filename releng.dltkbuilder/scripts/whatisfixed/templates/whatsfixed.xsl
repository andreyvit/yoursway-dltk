<xsl:stylesheet version='1.0'
	xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
	xmlns:msxsl="urn:schemas-microsoft-com:xslt"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:cvs="http://releng.wtp.eclipse.org/cvstools"
	xsi:schemaLocation="./cvstools.xsd
         http://www.w3.org/1999/XSL/Transform
         http://www.w3.org/1999/XSL/Transform.xsd
         http://www.w3.org/1999/xhtml
         http://www.w3.org/1999/xhtml.xsd">
	<xsl:output method="html" encoding="ISO-8859-1" />
	<xsl:param name="cvsServer">http://dev.eclipse.org</xsl:param>
	<xsl:param name="cvsparameter">WebTools_Project</xsl:param>
	<xsl:template match="/">
		
			<html>
			<head>
			    <link rel="stylesheet" href="buglog_style.css" type="text/css"/>
				<script language="javascript">
     				 function twist(img, id) {
					        var div = document.getElementById(id);
						     if (div.style.display == ""){
						          div.style.display = "none";
						          img.src = "images/twistclosed.gif";
        					  }
					        else{
						          div.style.display = "";
						          img.src = "images/twistopened.gif";
        					}
      				}
				    var isTwistOpen = false;
      				function twistAll(){
      					        		
					        var divs = document.getElementsByTagName("div");
					        for (var i = 0; i &lt; divs.length; i++) {
					        var img = document.getElementById(divs[i].id + ".img");
			 		        if (isTwistOpen){
					            divs[i].style.display = "none";
					            img.src = "images/twistclosed.gif";
	          				 }
					         else {
					            divs[i].style.display = "";
					            img.src = "images/twistopened.gif";
	          				 }
	        		}

			        var a = document.getElementById('allTwistId');
			        if (isTwistOpen)
				          a.childNodes[0].data = "Expand All";
        			else
				          a.childNodes[0].data = "Collapse All";
			        isTwistOpen = !isTwistOpen;
			        }	
			        
			       
			    </script>
    		</head>
			<body>
				<script>
					
				</script>
				<xsl:for-each select="cvs:bugLog">
					<table>
						<tr>
							<td align="left" colspan="3"><a id="allTwistId" href="javascript:twistAll()">Expand All</a></td>
						</tr>
						
						<tr>
							
								<td align="left"  colspan="2"><h4> What's fixed</h4></td>
								<td><h4><xsl:value-of select="cvs:toBuild"/></h4></td>
							
						</tr>
						<tr>
						
								<td align="left"><h4>Since</h4></td>
								<td>:</td>
								<td><h4><xsl:value-of select="cvs:fromBuild"/></h4></td>
							
						</tr>
					</table>
						
							
						<xsl:for-each select="cvs:bugs">
							<xsl:sort select="cvs:no"/>
													   
							<br></br>
			
									<img id="{cvs:no}.img" src="images/twistclosed.gif" onclick="javascript:twist(this, '{cvs:no}')"/>
	
										[<a class="green-no-underline" 	href="https://bugs.eclipse.org/bugs/show_bug.cgi?id={cvs:no}" 	target="_bugz"> <xsl:value-of select="cvs:no" /></a>]

										<xsl:value-of select="cvs:summary" />
					
						
							<div id="{cvs:no}"  style="display:none">
													 
									<table class="revisions" id="revisionTable">
									
										   <tr> 
										     
										   		<th class="file">
										   			File
										   		</th>
												<th>
													Date
												</th>
												<th>
													Author
												</th>
												<th>
													Rev
												</th>
												<th>
													Info
												</th>
													
												<th>
													Target MilesStone
												</th>
												<th>
													Ver
												</th>
											  	
											</tr>
									

														   											
									<xsl:for-each select="cvs:delta">
										<xsl:variable name="count"><xsl:value-of select="position()"/></xsl:variable>
										<xsl:variable name="fileNoCVSRoot"><xsl:choose>
											<xsl:when test="starts-with(cvs:file,'//cvsroot/webtools')">
											<xsl:value-of select="substring-after(cvs:file,'//cvsroot/webtools')" />
											</xsl:when>
											<xsl:otherwise><xsl:value-of select="cvs:file" /></xsl:otherwise>
										</xsl:choose></xsl:variable>
										
										<xsl:variable name="projPath"><xsl:choose>
											<xsl:when test="contains(cvs:file,'jst.')">/viewcvs/index.cgi/*checkout*</xsl:when>
											<xsl:when test="contains(cvs:file,'wst.')">/viewcvs/index.cgi/*checkout*</xsl:when>
											<xsl:otherwise>/viewcvs/indexwebtools.cgi/~checkout~</xsl:otherwise>
										</xsl:choose></xsl:variable>
										
										<xsl:variable name="cvsPath"><xsl:value-of select="concat($cvsServer,$projPath,$fileNoCVSRoot)" /></xsl:variable>
										   
										   
										
													  
										<xsl:for-each select="cvs:revision">
										   
										   	
											
											
											<tr class="row{$count mod 2}">
												<xsl:variable name="rev"><xsl:value-of select="cvs:rev" /></xsl:variable>
												<xsl:variable name="fileUrl"><xsl:value-of select="concat($cvsPath,'?rev=',$rev,'&amp;cvsroot=WebTools_Project')" /></xsl:variable>
												<td class="bugFile">
													<a href="{$fileUrl}" target="_file"><xsl:value-of select="translate(substring-after($fileNoCVSRoot,path),'/',' ')" /></a>
												</td>
												<td class="bugDate">
													<xsl:value-of select="cvs:date" />
												</td>
												<td>
													<xsl:value-of select="cvs:author" />
												</td>
												<td>
													<xsl:value-of select="cvs:rev"/>
												</td>
												<td>
													<table>
														
														<tr>
															<td>
																<xsl:value-of select="cvs:bugzilla-priority" />
															</td>	
														</tr>
														<tr>
															<td>
																<xsl:value-of select="cvs:bugzilla-resolution" />-<xsl:value-of select="cvs:bugzilla-status" />
															</td>	
															
														</tr>
														<tr>
															<td>
																<xsl:value-of select="cvs:bugzilla-severity" />
															</td>	
														</tr>
														
													</table>
												</td>
												
												<td>
													<xsl:value-of select="cvs:bugzilla-targetMilestone" />
												</td>
												<td>
													<xsl:value-of select="cvs:bugzilla-version" />
												</td>
												
											 </tr>
											 

									    </xsl:for-each>
									</xsl:for-each>
									
									</table>
							  
							</div>
						
						</xsl:for-each>
				
				</xsl:for-each>
			</body>
		</html>
		
	</xsl:template>



</xsl:stylesheet>
