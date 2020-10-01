# critter

Demonstrating automerge

## Overview

A ClojureScript UI to emulate multiple applications that sync state
using the Automerge CRDT library.

## Setup

To get an interactive development environment run:

    lein figwheel

and open your browser at [localhost:3449](http://localhost:3449/).

To clean all compiled files:

    lein clean

To create a production build run:

    lein do clean, cljsbuild once min

And open your browser in `resources/public/index.html`. You will not
get live reloading, nor a REPL. 

## License

Copyright © 2020 Timothy Pratley

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
