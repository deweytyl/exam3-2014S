# A quick-and-dirty makefile for making the tarball for the exam

# +----------+--------------------------------------------------------
# | Settings |
# +----------+

# The user.  Please replace with your username.
USER=rebelsky

# The files that go in the tarball.  If you add things to the repo,
# you should update this.
FILES= \
        $(USER)/Makefile \
        $(USER)/README.md \
        $(USER)/.git \
        $(USER)/.gitignore \
        $(USER)/Problem1 \
        $(USER)/Problems23/.classpath \
        $(USER)/Problems23/.project \
        $(USER)/Problems23/src/*.java \
        $(USER)/Problem4/.classpath \
        $(USER)/Problem4/.project \
        $(USER)/Problem4/src/*.java 

# +------------------+------------------------------------------------
# | Standard Targets |
# +------------------+

.PHONY: default
default: tarball

# +----------------+--------------------------------------------------
# | Custom Targets |
# +----------------+

# Create the standard tarball
.PHONY: tarball
tarball: $(USER).tar.gz

$(USER).tar.gz: $(FILES)
	tar -C .. cvzf $@ $^
