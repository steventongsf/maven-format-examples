#!/usr/bin/env ruby 

# parse file to get rules
# <setting id="org.eclipse.jdt.core.formatter.insert_space_after_ellipsis" value="insert"/>

require "rexml/document"
include REXML
xmlfile = File.new("../resources/checkstyle/checkstyle-format-rules.xml")
xmldoc = Document.new(xmlfile)
# Now get the root element
root = xmldoc.root
# org.eclipse.jdt.core.formatter.
xmldoc.elements.each("module/module") do |e|
	name = e.attributes["name"]
	puts name
end
xmldoc.elements.each("module/module/module") do |e|
	name = e.attributes["name"]
	puts name
end