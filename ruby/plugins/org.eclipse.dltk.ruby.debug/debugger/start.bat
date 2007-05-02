@ECHO OFF

SET DBGP_RUBY_HOST=localhost
SET DBGP_RUBY_PORT=12000
SET DBGP_RUBY_KEY=test
SET DBGP_RUBY_SCRIPT=F:\programming\java\dltk\org.eclipse.dltk.ruby.debug\debugger\test.rb 
SET DBGP_RUBY_LOG=C:/dbgp_log.txt

"C:/ruby/bin/ruby.exe"  -Idbgp dbgp/debugger.rb