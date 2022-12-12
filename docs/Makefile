all: $(wildcard _posts/*.adoc)
	@echo done all

_posts/%.adoc : _posts/%.adoc.jam
	jbang jamal@verhas -open='{%' -close='%}' $@.jam $@