JAVAC=/usr/bin/javac
.SUFFIXES: .java .class

SRCDIR=src
BINDIR=bin

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES=Tree.class TreeCalculation.class \
		SequentialSunlightApp.class ParallelSunlightApp.class

CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

run:
	java -cp $(BINDIR) SequentialSunlightApp "sample_input.txt" "output1.txt"
	java -cp $(BINDIR) ParallelSunlightApp "sample_input.txt" "output2.txt"

clean:
	rm $(BINDIR)/*.class