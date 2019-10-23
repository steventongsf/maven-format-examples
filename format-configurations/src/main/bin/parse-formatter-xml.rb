#!/usr/bin/env ruby 

# parse file to get rules
# <setting id="org.eclipse.jdt.core.formatter.insert_space_after_ellipsis" value="insert"/>

require "rexml/document"
include REXML
xmlfile = File.new("../resources/eclipse/eclipse-format-rules.xml")
xmldoc = Document.new(xmlfile)
# Now get the root element
root = xmldoc.root
# org.eclipse.jdt.core.formatter.
xmldoc.elements.each("profiles/profile/setting") do |e|
	id = e.attributes["id"]
	id = id.gsub("org\.eclipse\.jdt\.core\.formatter\.","").gsub("_"," ")
	puts id
end