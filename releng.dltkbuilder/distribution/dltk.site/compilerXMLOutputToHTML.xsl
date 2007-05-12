<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet
    version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output
        method="html"
        indent="yes" />

    <xsl:template match="/">
        <html>
            <body>
                <xsl:apply-templates select="compiler" />
            </body>
        </html>
    </xsl:template>

    <xsl:template match="compiler">
        <h2>Compiler Report</h2>
        <p>
            Compiler:
            <xsl:value-of select="@name" />
            <xsl:text>   </xsl:text>Version:
            <xsl:value-of select="@version" />
        </p>

        <xsl:apply-templates select="stats"/>

        <xsl:apply-templates select="sources" />
    </xsl:template>

    <xsl:template
        name="stats"
        match="stats">
        
        <p>Number of source files: <xsl:value-of select="count(..//source)"/>
        Number of classfiles: <xsl:value-of select="number_of_classfiles/@value"/>
        </p>
        <p>
        Problems: <xsl:value-of select="problem_summary/@problems"/>
        (Errors: <xsl:value-of select="problem_summary/@errors"/>
        Warnings: <xsl:value-of select="problem_summary/@warnings"/>
        )        
        </p>
    </xsl:template>


    <xsl:template
        name="sources"
        match="sources">

        <xsl:if test="count(source) > 0">

            <dl>
                <xsl:for-each select="source">

                    <xsl:variable
                        name="package"
                        select="normalize-space(@package)">
                    </xsl:variable>
                    <xsl:variable
                        name="path"
                        select="normalize-space(@path)">
                    </xsl:variable>
                    <xsl:variable
                        name="classname"
                        select="substring-after($path, $package)">
                    </xsl:variable>
                    <xsl:if test="count(problems) > 0">
                        <dt style="font-weight: bold; color: black">
                            Source File:
                            <xsl:value-of select="$package" />
                            <xsl:value-of select="$classname" />
                        </dt>
                        <xsl:apply-templates select="problems" >
                            <xsl:with-param name="classname"><xsl:value-of select="$classname" /></xsl:with-param>
                        </xsl:apply-templates>
                    </xsl:if>
                </xsl:for-each>
            </dl>

        </xsl:if>

    </xsl:template>

    <xsl:template match="problems">
        <xsl:param name="classname" />
        <xsl:for-each select="problem">

            <dd style="font-weight: normal; color: black">
                <xsl:value-of select="position()"/>
                <xsl:text>.  </xsl:text>
                <xsl:value-of select="@severity" />
                <xsl:text>:  </xsl:text>
                <xsl:value-of select="@id" />
                <xsl:variable
                        name="lineNumber"
                        select="@line">
                </xsl:variable>
                <xsl:for-each select="message">
                    <p style="margin-left: +.5in; font-size: -1; margin-top: 0;margin-bottom:0;">
                        <small>
                            <xsl:value-of select="@value" />
                        </small>
                    </p>
                </xsl:for-each>
                <xsl:for-each select="source_context">    
                    <xsl:variable
                        name="pre"
                        select="substring(@value,0,(@sourceStart + 1))">
                    </xsl:variable>
                    <xsl:variable
                        name="main"
                        select="substring(@value,(@sourceStart + 1),(((@sourceEnd + 1) - (@sourceStart + 1)) + 1))">
                    </xsl:variable>
                    <xsl:variable
                        name="end"
                        select="substring(@value,(@sourceEnd + 2))">
                    </xsl:variable>
                 
                    <p style="margin-left: +.5in; font-size: -2; margin-top: 0;margin-bottom:0;">
                        <xsl:value-of select="substring($classname,2)"/>:
                    </p>
                    <p style="margin-left: +.5in; font-size: -2;font-family: monospace; margin-top: 0;margin-bottom:0;">
                        <xsl:value-of select="$lineNumber"/>: 
                        <xsl:value-of select="$pre" />
                        <b><u>
                        <xsl:value-of select="$main" />
                        </u></b>
                        <xsl:value-of select="$end" />
                    </p>
                </xsl:for-each>
            </dd>
        </xsl:for-each>


    </xsl:template>

</xsl:stylesheet>