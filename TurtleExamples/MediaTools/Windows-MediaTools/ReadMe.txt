To start the Squeak MediaTools double click on SqueakVM.exe.  You can also drag the mediatools-v5-sa.image
and drop it on the SqueakVM.exe.  

When Squeak starts you will see small windows that say SoundTools, PixelTools, and VideoTools.  Click on a
window to use that tool.  You can click on the small Home window to get back to the first
window or you can directly click on another window to go to that one. 

With the PixelTools you can open an image by picking it with a file chooser and then see 
a magnified area around the cursor in a small circle window as well as the whole image.  You can
click the mouse and see the x, y, red, green, and blue values at the cursor.  You also have a button
that will scale a JPEG picture.

With the SoundTools you can open a sound (.wav) file by picking it with a file chooser and then 
play it or open a Wave Viewer on it.  A Wave Viewer will let you show the FFT at the cursor.  
You can also record a sound. 

Click on the "Quit" button to exit from Squeak.  
__________________________________

If you are not already reading this in Squeak, but wish to...
Start Squeak and click in an open space to get the screen menu;
	choose 'open...' then 'file list'.
Select this file (readme.txt) in the top right pane.

The Squeak User Interface
----------------------------------
Windows
To close a window, click on the "X" at the left side of its title bar.  To collapse or expand a window, click on the "o" on the right side of its title bar.  To change the name of a window, use the "change title..." command in the menu that pops up when you click on the menu icon to the right of the window close "X" in the title bar.  This is for Morphic windows.  If you are in an MVC project, the windows look slightly different but have similar controls.

A window can be moved by dragging its title bar.  To change the size of a window, move the mouse near a corner or border and, when a resizing icon appears, click and drag.

Panes
Many windows are made up of panes, and many of these have scroll bars, which can be on the left or on the right, and which can be permanently in place or of the flop-out variety (see Preferences.)  The solid-colored rectangle can be dragged up and down to scroll the window contents.  The up and down arrows at the ends of the scroll bar scroll one line at a time.  The box with a "-" at the top of the scroll bar allows you to pop up a menu for that pane.

Menus
Pane menus (often different in different panes) can also be invoked by left-click (option-click on the Mac) in most panes.  Many menu commands can also be invoked by cmd (alt) key combinations, indicated in the menus.  The "desktop menu", also called the "screen menu" or the "World menu," can be invoked simply by clicking on the Squeak desktop outside of any window.

Preferences
There are numerous factors that influence the appearance and the behavior of Squeak.  Many of them are governed by "Preferences", which are yes/no settings under your control.  You can view and change preferences by opening a Preferences tool, either by dragging one out from the Tools flap or by choosing "preferences" from the "help..." branch of the desktop menu.  Additionally, many appearance-related controls can be reached via the "appearance..." branch of the desktop menu.

Themes
A number of preferences can work together to create a whole "look and feel" to the user interface.  Squeak gives you control at this level through support of "themes".  Click on the 'Squeak' flap, and then on the 'choose theme...' button to choose another theme for your Squeak.  A wimpy theme (outOfTheBox) was intentionally installed at release time (*) so that new users would be motivated to try other themes and, eventually, to define new ones of their own.  Note many aspects of a new theme will only appear on windows created after the change.  Therefore a good way to test a new theme is first to choose the theme, and then open a new browser or file list.

[(*) Just kidding.  No one agrees about such things, and a number of the creators of Squeak do not like the outOfTheBox setting.  However it was agreed by all that there would be some value in choosing preferences that were not too shocking to new users of Squeak].

Color
Squeak graphics support 1, 2, 4, 8, 16, and 32-bit color.  To change the resolution of the Squeak screen, choose from the screen menu 'appearance...', 'set display depth...'. You will get the best performance if the Squeak screen depth matches the color setting of your monitor. Note that Squeak's 16-bit depth corresponds to "thousands of colors" and its 32-bit depth corresponds ot "millions of colors" or "24-bit color".

Projects
A project is an entire Squeak desktop full of windows.  Projects are can be used to change quickly from one task to another.  An inactive project is represented by a project window that shows a thumbnail of that project's windows.  Project windows are actually more like doors than windows, since you can enter the project just by clicking on its project window.  You can create a new project by choosing 'open...project' from the screen menu.  To exit a project (and return to its parent project), choose 'previous project' from the screen menu.  Each project maintains its set of windows, plus its own set of Smalltalk changes and its own screen color depth.  A project can be stored on a Super Swiki, so projects are also the unit of sharing and publishing.

Morphic Halos
In a morphic project, cmd-click (alt-click) on a window or other graphical object will bring up a constellation of colored circles called "halo handles" around that object.  Additional clicks will cycle through the halos for other graphical objects in the nesting structure.  If you hold down the Shift key while cmd-clicking, the nested morphs will be traversed from innermost outward.  Clicking without the cmd (alt) key will dismiss the halo.  While the halo is up, letting the cursor linger over one of the halo handles for a few seconds will cause a balloon to pop up with the name of that handle.  Three useful handles are the top-left "X" handle (delete), the bottom-right yellow handle (resize), and the brown handle (slide the object within its containing object).  Halos allow complex graphical objects to be explored--or even disassembled (using the black halo handle).  Usually no harm results from taking apart an object; you can just discard the pieces and create a new one.

Flaps
Tabs labeled "Squeak", "Tools", "Supplies", etc., will be found along the edges of the Squeak desktop.  Click on any tab to open the corresponding flap.  Drag a tab to resize the flap and to relocate the tab.  Bring up the halo on any tab and click on its menu handle to be presented with many options relating to the flap.  Use the "Flaps..." menu, reached via the desktop menu, to control which flaps are visible and for other flap-related options and assistance.

Parts Bins
You can obtain new objects in many ways.  The "Objects Catalog" (choose "objects' from the desktop menu) and several of the standard flaps (e.g. "Tools" and "Supplies") serve as "Parts Bins" the for new objects.  Drag any icon you see in a Parts Bin and a fresh copy of the kind of object it represents will appear "in your hand"; click to deposit the new object anywhere you wish.  You can also add your own objects to any of the flaps -- just drag your object over the tab, wait for the flap to pop open, then drop the object at the desired position in the flap.

Typing
The assignment operator in Squeak, the "_" character, is really the ASCII underbar character, and that's how you type it.  Note that the two-character string ":=" is an acceptable alternative for assignment. Similarly, the "^" is the ASCII caret character, usually typed as shift-6 on U.S. keyboards.

The Squeak text editor supports many editing operations that can be invoked by command keys.  For a full list of these operations, choose 'help...' from the desktop menu, then choose 'command-key help'.  Try these out -- they will save you much time.  The command key is the key marked with an Apple on Mac keyboard and the key marked "ALT" on other keyboards.


Managing and Saving Changes
-------------------------------------
Starting and Quitting
Obviously you have figured out how to start the system.  One way is to double-click on an image.  If you have several different interpreters, you may want to drag the image to the appropriate interpreter; that lets you decide which interpreter should be used.

To quit a Squeak session, choose 'quit' with or without saving from the desktop menu.  If you save, your previous image file will be overwritten.  You may choose 'save as...' or 'save as new version' to save a copy of your image and changes files with a new name (see below).

Image File
All of the objects -- classes, dictionaries, windows and other objects -- that make up the Squeak environment are stored in an image file (this must be named 'SomeName.image' or 'SomeName.ima').  When you start up an image, everything is right where you left it when you last saved that image.

Sources and Changes
The source code associated with the Squeak code in an image file is stored in two other files.  The code of the base system (e.g., Squeak version 3.0) is stored in the file 'SqueakV3.sources', and the sources for methods added or changed since that time are in the changes file (which must similarly be named 'SomeName.changes').

Storing the source code in a separate file has several advantages.  To begin with, if you have been working for a couple of hours, and your dog pulls out the power cord, you will still have a sequential record of all your program edits, and these can be perused and replayed to recover your work.  This feature has also saved many a hacker who got too adventurous while changing the system he or she was using.

However, if you wish to run the system with severely limited resources, it can be operated without any source code, owing to its ability to decompile the bytecode methods into a readable and editable version of the original source code (only comments and temporary variable names are lost).

Finally, since the changes file does not consume memory space, Squeak keeps a complete history of all your program changes.  This makes it easy to examine or even reinstate older versions of methods (see 'versions' option in browser selector pane).  This encourages experimentation, since you can easily revert to the original versions of any set of methods.

FileOut, FileIn
In addition to the 'save' command that saves the entire state of your Squeak image, individual methods, categories and classes may be 'filed out'.  Filing out a method, category, or class results in the creation of a text file containing the code in question.  This file can be read into the same or another Squeak image to recreate the saved classes and methods.

ChangeLists, ChangeSets, and ChangeSorters
A ChangeList is a method-by-method view of a fileOut.  Note that the changes file records all your programming actions using the same fileOut format, so a ChangeList can browse the change history of any Squeak image.  The "recently logged changes" command of the changes... menu is one way to do this. You can also open a ChangeList on any fileOut file by selecting the file in the FileList and selecting the "browse changes" command.

In addition to the image-wide record of changes kept in the changes file, a record of changes is also associated with every project.  This "change set" records only the class and method changes you made within that project. This allows you to make a fileOut of all the changes that constitute your work on that project.  Single and dual ChangeSorters allow one to examine the change set of the current project and other projects, and also allows changes to be moved between change sets.  These are very useful tools for more experienced Squeak programmers.

Organizing your Disk
Squeak will look for the sources file either in the folder containing the image.  If the sources file is not found there, then it looks in the folder containing the VM.  In general, it is simplest to keep a single copy of the sources file in the folder containing the VM.  You can use any number of image/changes pairs anywhere on your disk.

If you wish to maintain several versions of the VM, here is the easiest way:  place all VMs in one folder along with the sources file.  Then, in each folder with images for version X, place an alias of the VM for version X.  You can then start VM version X on that image by dragging the image onto the VM alias.  (If you start Squeak by double-clicking on the image, it might use the wrong version of the VM to run that image.)  Another technique is to keep an alias for your favorite VM on the desktop and start images by dropping them on this alias.  These instructions apply to Mac and Windows, but the same general strategy can be applied to Linux, Unix, and many other platforms.


Morphic and MVC
---------------------
Squeak has two completely independent user interface frameworks, each with its own window system.  The newer Morphic framework is the locus current development and most of the interesting facilities, such as end-user scripting, work only in Morphic.  However, the older "Model-View-Controller" (MVC) framework is still a viable Smalltalk programming environment and may perform better on slower machines and when memory is extremely limited.  The choice of frameworks is made when you create a project using the "open..." command on the screen menu. You can have any mixture of Morphic and MVC projects within an image.


Brainstorming and Engineering
-------------------------------------
The Squeak team works in periods of expansion, when we try new approaches and write lots of new code, and periods of reflection, where we re-factor, clean up and document (well... when there's nothing else to do ;-).  It is important for critics to understand that the morphic system and the end-user programming systems that are embedded in it, are still in an expanding brainstorming phase.  We know that the morphic protocols are overgrown and unwieldy.

But there's a good reason for this.  Morphic is being taken in new directions, including flexing, scripting, and viewing.  When we have gained experience with these new areas, when we have done some testing with real users, when we better understand the real kernel of this new architecture, then we will clean up and simplify the architecture.


Source Code Updates
------------------------
Tired of waiting months for the next release?

You can load the latest updates automatically into any Squeak attached to the internet.  Just hit 'load code updates' in the Squeak flap, or choose 'update code from server' in the 'help...' menu.  The Squeak team periodically releases approved changes to Squeak to the update servers.  Updates are numbered and are loaded in order.

Active Squeak developers work with an advanced version of Squeak for which new updates are issued weekly or even daily.  If you wish to participate as a "test pilot" in this process, you should join the Squeak mailing list (described on Squeak.org), get a current "test pilot" image, and watch for announcements of new updates.

If you wish to set up your own Update server for your own organization, please contact Ted Kaehler.  It is an easy way to distribute changes to a group of people using Squeak.


Image Size
--------------
We have intentionally included more features with the Squeak 3.2 release than most people will use.  If space is of concern, many of these facilities can be removed to produce a considerably smaller image.

We are in the process of sanitizing and automating this removal process.  Right now, if you fileIn MajorShrinkFor3.0.cs, and follow the instructions, you should end up with an image that is around 870k.  It will not have Morphic in it, and there will be loose ends in the image that may cause errors when you attempt to use facilities that have been removed, but this is usually not fatal.  The shrunken image has compiled method temp names into a compact trailer on every method, allowing the entire system to be browsed by decompiling with temp names preserved.  This means there is no need to store the sources file on small machines.  While comments are not available after abandoning sources, all the code you write will be preserved properly in the changes file, so that you can upload it to a full Squeak when you return from your backpack trip.  

We will be updating the various shrinking routines to improve this process, and they can be browsed in the 'shrinking' category of SystemDictionary.  If you simply want a small image to play with, search for the mini2.2 image on the Squeak servers.  It is quite complete and only 600K.


The Squeak Wiki Wiki Server
-----------------------------------
Ward Cunningham invented the idea of a web server with pages that any user can modify. He called it the "Wiki Wiki" server, after the Hawaiian word for "quick".  Mark Guzdial and his students at Georgia Tech implemented a Wiki Wiki server in Squeak, which we call a "swiki".

Every web page on a Swiki web site has a button that says, "Edit this Page".  It gives you the contents of that page in a scrolling window.  If you edit this text and hit "Save", the page is stored back on the server with your changes.  This can work from any web browser.  Changing a page is easy enough that a workgroup, class, or organization can quickly create and maintain an evolving web site of its own pages.

To start your own Swiki, see the instructions in (PWS class howToStart), and get a folder with necessary template files from...
  http://www.cc.gatech.edu/fac/mark.guzdial/squeak/pws/  

Many thanks to Georg Gollmann, Mark Guzdial, Mark's students, and to the father of the Wiki Wiki, Ward Cunningham.


Stylized Text and Links in Source Code
--------------------------------------------
Squeak allows creation of hyperlinks in text, and preserves them (and most text styles) in source code and class comments!  This makes it possible to document Squeak more effectively than before, as you will see from the limited examples in the Sample Documentation window.  Links can be created using CMD-6, and they can deactivated by selecting (with an extra leading character, or from back to front) and using CMD-0.

If you never put links or emphasis in your source code, everything should work just as before.  FileOuts may include style information after each method.  If you need to bring a new fileOut into an older system, read the file 'readFileinsWithStyle.cs' into your older system first.


Sources of Information
---------------------------
Basic help information is available in two external media:

(1)  The ReadMe.txt file, which contains exactly the content you are currently reading.

(2)  You will also find lots of other useful and possibly more current information on the Georgia Tech Swiki server.  We thank Mark Guzdial at Georgia Tech for making this server available.  You can browse it in any web browser using the URL below, or, if you enjoy using Squeak for everything, just click on...
	http://minnow.cc.gatech.edu/Squeak.1

In addition, you will likely want to browse other sites on the web, including...
http://www.squeak.org/  -- The Squeak home page and UIUC archive
http://www.create.ucsb.edu/squeak/  -- Stephen Pope's U.S. mirror site at UCSB.
ftp://st.cs.uiuc.edu/Smalltalk/Squeak/  -- The Squeak archive at UIUC.
http://www.sugarWeb.com  -- Smalltalk User Group of Argentina (SUGAR)


Text of the Welcome Window
----------------------------------
In case you delete your welcome window, and wish to retrieve it for one reason or another, here is the text from that window, as released...

                  Squeak 3.2

Squeak is a work in progress based on Smalltalk-80, with which it is still reasonably compatible.  Every Squeak release includes all source code for the Squeak system, as well as all source code for its Virtual Machine (VM, or interpreter, also written in Smalltalk).
	Browser openBrowser
[Blue items in this window are active text.  If an item contains a URL, it will require internet access and may take a while to load].

Not only is all source code included, and changeable at will, it is also completely open and free.  The Squeak system image runs bit-identically across all platforms, and VMs are available for just about every computer and operating system available.  The history of the Squeak project can be read at
	ftp://st.cs.uiuc.edu/Smalltalk/Squeak/docs/OOPSLA.Squeak.html
The Squeak license and most other relevant information can be found on the Squeak Home Page,
	http://www.Squeak.org

Morphic
This release of Squeak uses the Morphic graphics architecture.  Squeak also includes an MVC architecture available inside MVC projects (see the world menu 'open...' options).  Most of the standard system windows can be opened in either framework, but media support is much more highly developed in Morphic.  In addition, the Morphic framework includes Genie, a character and gesture recognition system that allows you to control everything in Squeak by just using a pen.  Click on: 	AGenieIntroduction.

Projects
Projects are separate worlds within Squeak, similar to pages on the Internet.  In fact active projects can be shared over the internet just like web pages.  We have included a number of demonstration projects in the 'Worlds of Squeak' window.  Others, probably more recent than this release, may be found (someday) by downloading a stepping-stone project over the net:
	Project fromUrl:
		'http://209.143.91.36/super/SuperSwikiProj/Squeak%20News'.

To 'Go Back' from a project you have entered, choose 'previous project' from the world menu, or '< PREV' from the navigator bar.

Color graphics
Squeak's BitBlt has been retrofitted with support for variable-depth color and many performance enhancements.  It has several added functions including a paint mode that supports transparency, and an alpha-blend mode for 32-bit color.  It also has a "warp-drive" variant that will scale, rotate, and otherwise deform bitmaps in a single pass.  Interested users will want to try
	Display restoreAfter: [WarpBlt test1]
and
	Display restoreAfter: [WarpBlt test3].

Sound
Squeak includes base classes and some simple primitives that support real-time background generation of sound and music.  Interested users may want to try
	AbstractSound stereoBachFugue play.
as well as the examples in the Worlds of Squeak.

Balloon
Squeak now includes a completely new outline-based graphics subsystem named Balloon.  Balloon graphics are independent of scale and rotation, and may be rendered simply or with 2 degrees of anti-aliasing.  For a quick demonstration, click on...
	(FlashMorphReader on: (HTTPSocket
		httpGet: 'http://66.216.14.119/flash3/orbit/orbit.swf'
		accept:'application/x-shockwave-flash'))
	processFile startPlaying openInWorld.
This example also demonstrates that Squeak includes a fairly complete implementation of the Flash3 graphics file format, with conversion to Balloon graphical objects.  Since it's all in Squeak, you can stop the player and take apart the morphic balloon objects.

Networking
Squeak supports network access.  If you are on a web-connected network, you might want to try...
	HTTPSocket httpShowGif:
		'http://squeak.org/Squeak2.0/midi/Squeakers.GIF'.
There are more examples in the Socket class.  Also included with this release is a complete WikiWiki server.  See the accompanying information on WikiWiki.

Squeak's FileList also supports network access.  Available servers are shown at the [] root, and new servers can be added with the 'add server...' menu command.

Finally, as mentioned above, Squeak can download active projects over the net...
	Project fromUrl: 
		'http://209.143.91.36/super/SuperSwikiProj/FinalFrontier'.


Development support
Squeak includes a mature program development system.  Most of the components of this system can be found in the world menu 'open...' options.  Most of the available functions can be surveyed in various menu choices, and also in the world menu 'help...' / 'command-key help' option.

Shrinking Squeak
If you wish to unload the "Worlds of Squeak" examples from this image, make a backup copy, execute the following five lines, close the window, and then save the resulting image...
	ProjectViewMorph allInstancesDo:
		[:m | (m hasProperty: #deleteWorldsOfSqueak) ifTrue:
			[Project deletingProject: m project. m delete]].
	Project rebuildAllProjects.
	ScriptingSystem spaceReclaimed

It is possible to shrink Squeak much more than this.  Earlier releases of Squeak came with a specially-prepared fileIn, with a name such as "MajorShrinkFor3.0.cs", which, when applied as described in accompanying instructions, could create an image that is under 1MB in size.  Squeak 3.2 does not come with such a majorShrink file-in, but you could use an earlier version of majorShrink as a point of departure, or, better still, search for up-to-date information about shrinking on the Squeak swiki.  Here is a good point of departure:
	http://minnow.cc.gatech.edu/squeak/majorshrink

The Squeak License
Squeak is distributed for use and modification subject to a liberal open source license.  [http://squeak.org/license.html].  Unless stated to the contrary, works submitted for incorporation into or for distribution with Squeak shall be presumed subject to the same license.

Portions of Squeak are:

Copyright (c) 1996 Apple Computer, Inc.
Copyright (c) 1997-2001 Walt Disney Company, and/or
Copyrighted works of other contributors.
All rights reserved.
