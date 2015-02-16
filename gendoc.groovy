def markdown(String fn) {
	def out = new StringBuilder()
	def err = new StringBuilder()
	def command = [ 'perl', 'md/Markdown.pl', fn ]
	def proc = command.execute()
	proc.waitForProcessOutput(out, err)
	if (proc.exitValue()) {
		def msg = "failed to run '${command[0]}' command (args: ${command.tail()})\n$err$out"
		print 'Warning: '
		println msg
		""
	} else {
		if (err) println "${err}"
		out.toString()
	}
}

// generate test documentation for tests in named directory
def documentTestSet(def out, File dir) {
	if (dir.isDirectory()) {
		out.writeLine "<li class=\"test-set\">$dir"
		out.writeLine '<ul>'
		for (f in dir.listFiles()) {
			if (isTest(f)) {
				documentTest(out, f)
			} else if (f.isDirectory()) {
				documentTestSet(out, f)
			}
		}
		out.writeLine '</ul></li>'
	}
}
// TODO http://code.stephenmorley.org/javascript/collapsible-lists

def documentTest(def out, File dir) {
	def description = new File(dir, 'description')
	out.writeLine "<li class=\"test\">$dir"
	if (description.isFile()) {
		out.writeLine description.text
	}
	out.writeLine "</li>"
}

def isTest(File dir) {
	dir.isDirectory() && (new File(dir, 'Test.java').isFile() ||
		new File(dir, 'Test.properties').isFile())
}

new File('index.html').withWriter { out	->
	out.writeLine """\
<html>
<head><title>JJ Test Suites</title></head>
<style type="text/css">
p code {
  background-color: #F8F8F8;
  border: 1px solid #CCC;
  border-radius: 3px;
  padding: 0px 5px;
  white-space: nowrap;
}
pre {
  background-color: #F8F8F8;
  border: 1px solid #CCC;
  border-radius: 3px;
  padding: 6px 10px;
  overflow: auto;
  width: 800px;
}</style>
<body>"""

	out.writeLine(markdown('README.md'))

	out.writeLine """\
<h2>Tests</h2>
<p>All available tests are listed below:</p>
<ul>
"""

	documentTestSet(out, new File('tests'))

	out.writeLine """\
</ul>
</body>
</html>
"""
}
