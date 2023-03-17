suffix = "-SNAPSHOT-shaded.jar"
target = "0"
latest = "0"

for each file in createObject("scripting.fileSystemObject").getFolder(".").files
	if right(file.name, len(suffix)) = suffix Then
		version = split(file.name, "-")(1)
		if version > latest then
			target = file.name
			latest = version
		end if
	end if
next

createObject("wscript.shell").run "javaw -jar " + target