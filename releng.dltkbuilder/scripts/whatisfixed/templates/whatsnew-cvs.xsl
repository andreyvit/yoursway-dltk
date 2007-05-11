<xsl:stylesheet version = '1.0' 
		xmlns:xsl='http://www.w3.org/1999/XSL/Transform' 
		xmlns:msxsl="urn:schemas-microsoft-com:xslt"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xmlns:cvs="http://releng.wtp.eclipse.org/cvstools"
		xsi:schemaLocation="./cvstools.xsd
         http://www.w3.org/1999/XSL/Transform
         http://www.w3.org/1999/XSL/Transform.xsd
         http://www.w3.org/1999/xhtml
         http://www.w3.org/1999/xhtml.xsd">
	<xsl:output method="html" encoding="ISO-8859-1"/>

	<!-- set default values here, and also in php script wrapper-->
	<xsl:param name="iesDate"></xsl:param>
	<xsl:param name="drvDate"></xsl:param>
	<xsl:param name="cvsServer">http://dev.eclipse.org</xsl:param>
	<xsl:param name="cvsparameter">WebTools_Project</xsl:param>
	<xsl:param name="source"/>

	<!-- points to a specific bug's logfile; not the same as Bugzilla, which is filterName4 -->
	<xsl:param name="bug"/>

	<xsl:param name="sortMethod1">date</xsl:param> 
	<xsl:param name="sortDir1">descending</xsl:param> 
	<xsl:param name="sortMethod2">file</xsl:param> 
	<xsl:param name="sortDir2">ascending</xsl:param> 

	<!-- up to 15 different filters can be applied -->
	<xsl:param name="filterName1">File</xsl:param>
	<xsl:param name="filterVal1"></xsl:param>
	<xsl:param name="filterName2">Date</xsl:param>
	<xsl:param name="filterVal2"></xsl:param>
	<xsl:param name="filterName3">Author</xsl:param>
	<xsl:param name="filterVal3"></xsl:param>
	<xsl:param name="filterName4">Bugzilla</xsl:param>
	<xsl:param name="filterVal4"></xsl:param>
	<xsl:param name="filterName5">Comments</xsl:param>
	<xsl:param name="filterVal5"></xsl:param>
	<xsl:param name="filterName6">bugzilla-summary</xsl:param>
	<xsl:param name="filterVal6"></xsl:param>
	<xsl:param name="filterName7">bugzilla-status</xsl:param>
	<xsl:param name="filterVal7"></xsl:param>
	<xsl:param name="filterName8">bugzilla-resolution</xsl:param>
	<xsl:param name="filterVal8"></xsl:param>
	<xsl:param name="filterName9">bugzilla-assignedTo</xsl:param>
	<xsl:param name="filterVal9"></xsl:param>
	<xsl:param name="filterName10">bugzilla-severity</xsl:param>
	<xsl:param name="filterVal10"></xsl:param>
	<xsl:param name="filterName11">bugzilla-priority</xsl:param>
	<xsl:param name="filterVal11"></xsl:param>
	<xsl:param name="filterName12">bugzilla-hardware</xsl:param>
	<xsl:param name="filterVal12"></xsl:param>
	<xsl:param name="filterName13">bugzilla-version</xsl:param>
	<xsl:param name="filterVal13"></xsl:param>
	<xsl:param name="filterName14">bugzilla-targetMilestone</xsl:param>
	<xsl:param name="filterVal14"></xsl:param>
	<xsl:param name="filterName15">bugzilla-os</xsl:param>
	<xsl:param name="filterVal15"></xsl:param>

	<xsl:param name="CVSPREWTP">.</xsl:param>

	<xsl:template match="/">
		<xsl:for-each select="cvs:rlog">
			<html>
			<head>
			<title>Eclipse Web Tools Platform: What`s New, CVS?</title>
			<style>@import url("../whatsnew-cvs.css");</style>
			</head>
			<body>
			<a name="top"> </a>
			<!-- iesDate: <xsl:value-of select="$iesDate" />, drvDate: <xsl:value-of select="$drvDate" /><br/> -->
			<b class="big-header">
				<xsl:choose>
					<xsl:when test="$drvDate=cvs:startdate and $iesDate=cvs:startdate">
						Between Last Uploaded Driver &amp; IES Refresh On <b class="navy"><xsl:value-of select="cvs:startdate" /></b> and <b class="navy"><xsl:value-of select="cvs:enddate" /></b>
					</xsl:when>
					<xsl:when test="$drvDate=cvs:startdate">
						Between Uploaded Driver On <b class="navy"><xsl:value-of select="cvs:startdate" /></b> and <b class="navy"><xsl:value-of select="cvs:enddate" /></b>
					</xsl:when>
					<xsl:when test="$iesDate=cvs:startdate">
						Between Last IES Refresh On <b class="navy"><xsl:value-of select="cvs:startdate" /></b> and <b class="navy"><xsl:value-of select="cvs:enddate" /></b>
					</xsl:when>
					<xsl:otherwise>
						Between 
						<b class="navy">
							<xsl:choose>
							<xsl:when test="contains(cvs:startdate,'00:00:00')">
								<xsl:value-of select="substring-before(cvs:startdate,'00:00:00')"/>
							</xsl:when>
							<xsl:otherwise>
								<xsl:value-of select="cvs:startdate"/>
							</xsl:otherwise>
							</xsl:choose>
						</b> and 
						<b class="navy"><xsl:value-of select="cvs:enddate"/></b>
					</xsl:otherwise>
				</xsl:choose>
			</b>
			<h4 class="green">CVS Deltas: <xsl:value-of select="cvs:counts/cvs:numDeltas"/>, Revisions: <xsl:value-of select="cvs:counts/cvs:numRevisions"/></h4>

			<table width="98%" border="0">
				<tr>
					<td><b></b></td> <!-- # --> 
					<td><b>Group</b></td>
					<td align="right"><b>C</b></td> <!-- link to CVS parent folder -->
					<td align="center"><b>V</b></td> <!-- link to CVS script detail page -->
					<td align="left"><b>S</b></td> <!-- link to CVS source (~checkout~) page -->
					<td align="left"><b>File</b></td> <!-- link to CVS source (~checkout~) page -->
					<td align="left"><b>Versions</b></td> <!-- link to CVS source (~checkout~) page -->
					
					<td width="100%" colspan="13">
						<table border="0" width="100%">
							<tr>
								<td align="left"><b>Date</b></td> <!-- link to CVS source (~checkout~) page -->
								<td align="left"><b>Author</b></td> <!-- link to CVS source (~checkout~) page -->
								<td align="left"><b>Bugzilla</b></td> <!-- link to CVS source (~checkout~) page -->
								<td align="left"><b>Comment</b></td> <!-- link to CVS source (~checkout~) page -->
								<td align="left"><b>Status</b></td> 
								<td align="left"><b>Resolution</b></td>
								<td align="left"><b>Assigned To</b></td>
								<td align="left"><b>Severity</b></td> 
								<td align="left"><b>Priority</b></td>
								<td align="left"><b>Hardware</b></td>
								<td align="left"><b>Version</b></td> 
								<td align="left"><b>Target Milestone</b></td>
								<td align="left"><b>OS</b></td>
					
							</tr>
						</table>
					</td>
					
					
				</tr>
				<xsl:for-each select="cvs:cvsdelta">
					<xsl:sort select="cvs:revision/*[name()=$sortMethod1]" order="{$sortDir1}"/> 
					<xsl:sort select="./*[name()=$sortMethod2]" order="{$sortDir2}"/>

					<!-- add filters -->
					<xsl:variable name="doRow">
					<xsl:choose>
						<xsl:when test="$filterName1='File' and $filterVal1!=''">
							<xsl:if test="contains(.,$filterVal1)">1</xsl:if> <!-- found -->
						</xsl:when>
						<xsl:when test="$filterName2='Date' and $filterVal2!=''">
							<xsl:for-each select="cvs:revision">
								<xsl:choose>
									<xsl:when test="contains(cvs:date,$filterVal2)">1</xsl:when> <!-- found -->
									<xsl:otherwise></xsl:otherwise>
								</xsl:choose>
							</xsl:for-each>
						</xsl:when>
						<xsl:when test="$filterName3='Author' and $filterVal3!=''">
							<xsl:for-each select="cvs:revision">
								<xsl:choose>
									<xsl:when test="contains(cvs:author,$filterVal3)">1</xsl:when> <!-- found -->
									<xsl:otherwise></xsl:otherwise>
								</xsl:choose>
							</xsl:for-each>
						</xsl:when>
						<xsl:when test="$filterName4='Bugzilla' and $filterVal4!=''">
							<xsl:for-each select="cvs:revision">
								<xsl:choose>
									<xsl:when test="contains(cvs:bugzilla,$filterVal4)">1</xsl:when> <!-- found -->
									<xsl:otherwise></xsl:otherwise>
								</xsl:choose>
							</xsl:for-each>
						</xsl:when>
						
						
						<xsl:when test="$filterName5='Comments' and $filterVal5!=''">
							<xsl:for-each select="cvs:revision">
								<xsl:choose>
									<xsl:when test="contains(cvs:comments,$filterVal5)">1</xsl:when> <!-- found -->
									<xsl:otherwise></xsl:otherwise>
								</xsl:choose>
							</xsl:for-each>
						</xsl:when>
						
						<xsl:when test="$filterName6='bugzilla-summary' and $filterVal6!=''">
							<xsl:for-each select="cvs:revision">
								<xsl:choose>
									<xsl:when test="contains(cvs:bugzilla-summary,$filterVal6)">1</xsl:when> <!-- found -->
									<xsl:otherwise></xsl:otherwise>
								</xsl:choose>
							</xsl:for-each>
						</xsl:when>
						
						<xsl:when test="$filterName7='bugzilla-status' and $filterVal7!=''">
							<xsl:for-each select="cvs:revision">
								<xsl:choose>
									<xsl:when test="contains(cvs:bugzilla-status,$filterVal7)">1</xsl:when> <!-- found -->
									<xsl:otherwise></xsl:otherwise>
								</xsl:choose>
							</xsl:for-each>
						</xsl:when>
						
						<xsl:when test="$filterName8='bugzilla-resolution' and $filterVal8!=''">
							<xsl:for-each select="cvs:revision">
								<xsl:choose>
									<xsl:when test="contains(cvs:bugzilla-resolution,$filterVal8)">1</xsl:when> <!-- found -->
									<xsl:otherwise></xsl:otherwise>
								</xsl:choose>
							</xsl:for-each>
						</xsl:when>
						
						<xsl:when test="$filterName9='bugzilla-assignedTo' and $filterVal9!=''">
							<xsl:for-each select="cvs:revision">
								<xsl:choose>
									<xsl:when test="contains(cvs:bugzilla-assignedTo,$filterVal9)">1</xsl:when> <!-- found -->
									<xsl:otherwise></xsl:otherwise>
								</xsl:choose>
							</xsl:for-each>
						</xsl:when>
						
						<xsl:when test="$filterName10='bugzilla-severity' and $filterVal10!=''">
							<xsl:for-each select="cvs:revision">
								<xsl:choose>
									<xsl:when test="contains(cvs:bugzilla-severity,$filterVal10)">1</xsl:when> <!-- found -->
									<xsl:otherwise></xsl:otherwise>
								</xsl:choose>
							</xsl:for-each>
						</xsl:when>
						
						<xsl:when test="$filterName11='bugzilla-priority' and $filterVal11!=''">
							<xsl:for-each select="cvs:revision">
								<xsl:choose>
									<xsl:when test="contains(cvs:bugzilla-priority,$filterVal11)">1</xsl:when> <!-- found -->
									<xsl:otherwise></xsl:otherwise>
								</xsl:choose>
							</xsl:for-each>
						</xsl:when>
						
						<xsl:when test="$filterName12='bugzilla-hardware' and $filterVal12!=''">
							<xsl:for-each select="cvs:revision">
								<xsl:choose>
									<xsl:when test="contains(cvs:bugzilla-hardware,$filterVal12)">1</xsl:when> <!-- found -->
									<xsl:otherwise></xsl:otherwise>
								</xsl:choose>
							</xsl:for-each>
						</xsl:when>
						
						<xsl:when test="$filterName13='bugzilla-version' and $filterVal13!=''">
							<xsl:for-each select="cvs:revision">
								<xsl:choose>
									<xsl:when test="contains(cvs:bugzilla-version,$filterVal13)">1</xsl:when> <!-- found -->
									<xsl:otherwise></xsl:otherwise>
								</xsl:choose>
							</xsl:for-each>
						</xsl:when>
						
						<xsl:when test="$filterName14='bugzilla-targetMilestone' and $filterVal14!=''">
							<xsl:for-each select="cvs:revision">
								<xsl:choose>
									<xsl:when test="contains(cvs:bugzilla-targetMilestone,$filterVal14)">1</xsl:when> <!-- found -->
									<xsl:otherwise></xsl:otherwise>
								</xsl:choose>
							</xsl:for-each>
						</xsl:when>
						
						<xsl:when test="$filterName15='bugzilla-bugzilla-os' and $filterVal15!=''">
							<xsl:for-each select="cvs:revision">
								<xsl:choose>
									<xsl:when test="contains(cvs:bugzilla-os,$filterVal15)">1</xsl:when> <!-- found -->
									<xsl:otherwise></xsl:otherwise>
								</xsl:choose>
							</xsl:for-each>
						</xsl:when>
						
												
						<xsl:otherwise>1</xsl:otherwise> <!-- no matching filters enabled, show all -->
					</xsl:choose>
					</xsl:variable>

					<!-- for testing only -->
					<!-- <xsl:value-of select="$doRow" /> -->

					<xsl:if test="contains($doRow,'1')">
						<tr valign="top">
							<xsl:choose>
								<xsl:when test="(position() mod 2 = 1)">
									<xsl:attribute name="class">dark-row</xsl:attribute>
								</xsl:when>
								<xsl:otherwise>
									<xsl:attribute name="class">light-row</xsl:attribute>
								</xsl:otherwise>
							</xsl:choose>

							<td align="right"><a name="{position()}"><xsl:value-of select="concat(position(),'.')" /></a></td>

							<xsl:variable name="fileNoCVSRoot"><xsl:choose>
								<xsl:when test="starts-with(cvs:file,'/cvsroot/webtools')">
									<xsl:value-of select="substring-after(cvs:file,'/cvsroot/webtools')" />
								</xsl:when>
								<xsl:otherwise><xsl:value-of select="cvs:file" /></xsl:otherwise>
							</xsl:choose></xsl:variable>
							<xsl:variable name="file"><xsl:value-of select="cvs:file" /></xsl:variable>
							<xsl:variable name="projPath"><xsl:choose>
								<xsl:when test="contains(cvs:file,'jst.')">/viewcvs/index.cgi/</xsl:when>
								<xsl:when test="contains(cvs:file,'wst.')">/viewcvs/index.cgi/</xsl:when>
								<xsl:otherwise>/viewcvs/index.cgi/checkout</xsl:otherwise>
							</xsl:choose></xsl:variable>
							<xsl:variable name="projDetailPath"><xsl:choose>
								<xsl:when test="contains(cvs:file,'jst.')">/viewcvs/index.cgi</xsl:when>
								<xsl:when test="contains(cvs:file,'wst.')">/viewcvs/index.cgi</xsl:when>
								<xsl:otherwise>/viewcvs/index.cgi</xsl:otherwise>
							</xsl:choose></xsl:variable>
							<xsl:variable name="cvsPath"><xsl:value-of select="concat($cvsServer,$projPath,$fileNoCVSRoot)" /></xsl:variable>
							<xsl:variable name="cvsDetailPath"><xsl:value-of select="concat($cvsServer,$projDetailPath,$fileNoCVSRoot,'/?cvsroot=',$cvsparameter)" /></xsl:variable>

							<xsl:for-each select="document('whatsnew-cvs.substitutions.xml')/subs/sub">
								<xsl:variable name="proj"><xsl:value-of select="substring-before(path,concat('/',catg))" /></xsl:variable>
								<xsl:variable name="projNoCVSRoot"><xsl:choose>
									<xsl:when test="starts-with($proj,'/cvsroot/webtools')">
										<xsl:value-of select="substring-after($proj,'/cvsroot/webtools')" />
									</xsl:when>
									<xsl:otherwise><xsl:value-of select="$proj" /></xsl:otherwise>
								</xsl:choose></xsl:variable>
								<xsl:choose>
									<xsl:when test="starts-with($file,path)">
										<td><a target="_cvs" class="black-no-underline" href="{concat($cvsServer,$projPath,$projNoCVSRoot,'/',catg,'/?cvsroot=',$cvsparameter)}"><b><xsl:value-of select="catg" /></b></a></td>
										<td width="15"><a target="_cvs" class="black-no-underline" href="{concat($cvsServer,$projPath,$projNoCVSRoot,'/',catg,'/?cvsroot=',$cvsparameter)}"><img border="0" alt="CVS project folder" src="images/icon-folder.gif"/></a></td>
									</xsl:when>
									<xsl:otherwise>
									</xsl:otherwise>
								</xsl:choose>
							</xsl:for-each>
									<!-- file links -->
									
									<xsl:variable name="rev"><xsl:value-of select="cvs:revision/cvs:rev" /></xsl:variable>
									<xsl:variable name="fileUrl"><xsl:value-of select="concat($cvsPath,'?rev=',$rev,'&amp;cvsroot=WebTools_Project')" /></xsl:variable>
									
									
									<td width="14"><a target="_cvs" class="black-no-underline" href="{$cvsDetailPath}"><img border="0" alt="CVS file details" src="images/icon-file-detail.gif"/></a></td>
									<td width="13"><a target="_cvs" class="black-no-underline" href="{$fileUrl}"><img border="0" alt="CVS file source (or download)" src="images/icon-file.gif"/></a></td>
									
									
									
									<td colspan="1"><a target="_cvs" class="black-no-underline" href="{$fileUrl}"><xsl:value-of select="translate(substring-after($fileNoCVSRoot,path),'/',' ')" /></a></td>
							<xsl:variable name="firstRev">
								<xsl:value-of select="cvs:revision/cvs:rev" />
							</xsl:variable>
							<xsl:variable name="lastRev">
								<xsl:for-each select="cvs:revision">
									<xsl:if test="position()=last()">
										<xsl:apply-templates select="cvs:rev"/> 
									</xsl:if>
								</xsl:for-each>
							</xsl:variable>
							<td><nobr>
								<xsl:choose>
									<xsl:when test="string($firstRev)=string($lastRev)">
										<i class="red-dark"><xsl:value-of select="$lastRev"/></i>
									</xsl:when>
									<xsl:otherwise>
										<a target="_cvs" class="red-no-underline" href="{$cvsPath}.diff?r1={$firstRev}&amp;r2={$lastRev}&amp;cvsroot={$cvsparameter}">
										<xsl:value-of select="$firstRev"/> 
										&gt; <xsl:value-of select="$lastRev" />
										</a> 
									</xsl:otherwise>
								</xsl:choose>
							(<span class="navy"><xsl:value-of select="cvs:branch"/></span>, 
							-<xsl:value-of select="cvs:keywords"/>)</nobr></td>
							<td width="100%" colspan="13">
								<table border="0">
									<xsl:for-each select="cvs:revision">

										<!-- add filters -->
										<xsl:variable name="doSubRow">
										<xsl:choose>
											<xsl:when test="$filterName2='Date' and $filterVal2!=''">
												<xsl:if test="contains(cvs:date,$filterVal2)">2</xsl:if> <!-- found -->
											</xsl:when>
											<xsl:when test="$filterName3='Author' and $filterVal3!=''">
												<xsl:if test="contains(cvs:author,$filterVal3)">2</xsl:if> <!-- found -->
											</xsl:when>
											<xsl:when test="$filterName4='Bugzilla' and $filterVal4!=''">
												<xsl:if test="contains(cvs:bugzilla,$filterVal4)">2</xsl:if> <!-- found -->
											</xsl:when>
											
											<xsl:when test="$filterName5='Comments' and $filterVal5!=''">
												<xsl:if test="contains(cvs:comments,$filterVal5)">2</xsl:if> <!-- found -->
											</xsl:when>
											
											
											<xsl:when test="$filterName6='bugzilla-summary' and $filterVal6!=''">
												<xsl:if test="contains(cvs:bugzilla-summary,$filterVal6)">2</xsl:if> <!-- found -->
											</xsl:when>
											<xsl:when test="$filterName7='bugzilla-status' and $filterVal7!=''">
												<xsl:if test="contains(cvs:bugzilla-status,$filterVal7)">2</xsl:if> <!-- found -->
											</xsl:when>
											<xsl:when test="$filterName8='bugzilla-resolution' and $filterVal8!=''">
												<xsl:if test="contains(cvs:bugzilla-resolution,$filterVal8)">2</xsl:if> <!-- found -->
											</xsl:when>
											<xsl:when test="$filterName9='bugzilla-assignedTo' and $filterVal9!=''">
												<xsl:if test="contains(cvs:bugzilla-assignedTo,$filterVal9)">2</xsl:if> <!-- found -->
											</xsl:when>
											<xsl:when test="$filterName10='bugzilla-severity' and $filterVal10!=''">
												<xsl:if test="contains(cvs:bugzilla-severity,$filterVal10)">2</xsl:if> <!-- found -->
											</xsl:when>
											<xsl:when test="$filterName11='bugzilla-priority' and $filterVal11!=''">
												<xsl:if test="contains(cvs:bugzilla-priority,$filterVal11)">2</xsl:if> <!-- found -->
											</xsl:when>
											<xsl:when test="$filterName12='bugzilla-hardware' and $filterVal12!=''">
												<xsl:if test="contains(cvs:bugzilla-hardware,$filterVal12)">2</xsl:if> <!-- found -->
											</xsl:when>
											<xsl:when test="$filterName13='bugzilla-version' and $filterVal13!=''">
												<xsl:if test="contains(cvs:bugzilla-version,$filterVal13)">2</xsl:if> <!-- found -->
											</xsl:when>
											<xsl:when test="$filterName14='bugzilla-targetMilestone' and $filterVal14!=''">
												<xsl:if test="contains(cvs:bugzilla-targetMilestone,$filterVal14)">2</xsl:if> <!-- found -->
											</xsl:when>
											<xsl:when test="$filterName15='bugzilla-os' and $filterVal15!=''">
												<xsl:if test="contains(cvs:bugzilla-os,$filterVal15)">2</xsl:if> <!-- found -->
											</xsl:when>
											
											
											
											<xsl:when test="$filterVal2='' and $filterVal3='' and $filterVal4='' and $filterVal5='' and $filterVal6='' and $filterVal7='' and $filterVal8='' and $filterVal9='' and $filterVal10='' and $filterVal11='' and $filterVal12='' and $filterVal13='' and $filterVal14='' and $filterVal15=''">2</xsl:when> <!-- no filter -->
										</xsl:choose>
										</xsl:variable>

										<!-- for testing only -->
										<!-- <xsl:value-of select="$doSubRow" /> -->

										<xsl:if test="contains($doSubRow,'2')">
											<tr valign="top">
												<td><nobr><xsl:value-of select="cvs:date"/></nobr></td>
												<td>
												<xsl:variable name="prevRev">
													<xsl:apply-templates select="cvs:rev"/>
												</xsl:variable>
												<xsl:choose>
													<xsl:when test="string(rev)!='1.1'">
														<a target="_cvs" class="red-no-underline" href="{$cvsPath}.diff?r1={rev}&amp;r2={$prevRev}"><xsl:value-of select="rev"/></a>
													</xsl:when>
													<xsl:otherwise>
														<i class="red-dark"><xsl:value-of select="cvs:rev"/></i>
													</xsl:otherwise>
												</xsl:choose>
												</td>
												<td><xsl:value-of select="cvs:author"/></td>
												<xsl:choose>
													<xsl:when test="cvs:bugzilla">
														<td>[<a class="green-no-underline" href="https://bugs.eclipse.org/bugs/show_bug.cgi?id={cvs:bugzilla}" target="_bugz"><xsl:value-of select="cvs:bugzilla"/></a>]</td>
													</xsl:when>
													<xsl:otherwise>
														<td>  </td>
													</xsl:otherwise>
												</xsl:choose>
												
												<xsl:choose>
													<xsl:when test="cvs:bugzilla-summary !=''">
														<td><xsl:value-of select="cvs:bugzilla-summary"/></td>
													</xsl:when>
													<xsl:otherwise>
														<td><xsl:value-of select="cvs:comments"/>  </td>
													</xsl:otherwise>
												</xsl:choose>
												
												<td>
													<xsl:value-of select="cvs:bugzilla-status"/>
												</td>
												
												<td>
													<xsl:value-of select="cvs:bugzilla-resolution"/>
												</td>
												
												<td>
													<xsl:value-of select="cvs:bugzilla-assignedTo"/>
												</td>
												
												<td>
													<xsl:value-of select="cvs:bugzilla-severity"/>
												</td>
												
												<td>
													<xsl:value-of select="cvs:bugzilla-priority"/>
												</td>
												
												<td>
													<xsl:value-of select="cvs:bugzilla-hardware"/>
												</td>
												
												<td>
													<xsl:value-of select="cvs:bugzilla-version"/>
												</td>
												
												<td>
													<xsl:value-of select="cvs:bugzilla-targetMilestone"/>
												</td>
												
												<td>
													<xsl:value-of select="cvs:bugzilla-os"/>
												</td>
											</tr>
										</xsl:if>
									</xsl:for-each>
								</table>
							</td>
						</tr>
					</xsl:if>
				</xsl:for-each>
			</table>
			</body>
			</html>
		</xsl:for-each>
	</xsl:template>

	<xsl:template match="cvs:rev">
		<xsl:if test="string(.)='1.1'">	<!-- simple case, 1.1 -->
			<xsl:value-of select="1.1" />
		</xsl:if>
		<xsl:if test="string(.)!='1.1'">
			<xsl:choose>
				<xsl:when test="contains(substring-after(substring-after(.,'.'),'.'),'.') and substring(.,string-length(.),1)!='1'"> 
					<!-- check if 1.6.2.2 : 1.6.2 . (2-1) : 1.6.2.1  -->
					<xsl:value-of select="concat(
						substring(.,1,string-length(.)-2),
						'.',
						number(substring(.,string-length(.)))-1
					)"/>
				</xsl:when>
				<xsl:when test="contains(substring-after(substring-after(.,'.'),'.'),'.') and substring(.,string-length(.),1)='1'"> 
					<!-- check if 1.6.2.1 : 1 . 6 : 1.6  -->
					<xsl:value-of select="concat(
						substring-before(.,'.'), 
						'.',
						substring-before(substring-after(.,'.'),'.')
					)"/>
				</xsl:when>
				<xsl:when test="contains(substring-after(.,'.'),'.') and substring(.,string-length(.),1)!='1'"> 
					<!-- check if 1.5.4 : 1.5 . (4-1) : 1.5.3 [is this a valid cvs range?] -->
					<xsl:value-of select="concat(
						substring(.,1,string-length(.)-2),
						'.',
						number(substring(.,string-length(.)))-1
					)"/>
				</xsl:when>
				<xsl:when test="contains(substring-after(.,'.'),'.') and substring(.,string-length(.),1)='1'"> 
					<!-- check if 1.5.1 : 1 . (5-1) : 1.4 [is this a valid cvs range?] -->
					<xsl:value-of select="concat(
						substring-before(.,'.'), 
						'.', 
						string(floor(number(substring-after(.,'.'))) - 1)
					)"/>
				</xsl:when>
				<xsl:otherwise>	
					<!-- check if 1.40 : 1 . (40-1) : 1.39 -->
					<xsl:value-of select="concat(
						substring-before(.,'.'),
						'.',
						number(substring-after(.,'.'))-1
					)"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:if>
	</xsl:template>

	<xsl:template match="colgroup">
		<td width="10"><b><xsl:value-of select="col/title" /></b></td>
	</xsl:template>
</xsl:stylesheet>
