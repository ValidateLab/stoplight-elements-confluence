# Stoplight elements plugin for Confluence

## Introduction

---

This is an Atlassian Confluence plugin project which allows to integrate video-resources from web, in example a link like video.com/example.mp4, in a Confluence page.<br/> The project was created by Atlassian SDK, for further information see below. The video plays as a stream from specified web resource. You can use this project for your uses.<br/>Further information about licensing you will find in LICENSE file.
<br/>

## Compatibility

---

* All common browser
* All common systems
* Tested compatibility with Atlassian Confluence 6 and higher
  <br/>

## Preview

---

In this section you can see the main graphic user interface inside the Confluence macro browser.<br/>
Please note that all picture in this preview was created by Firefox web-browser engine.<br/>
The layout was managed and based by Linux gnome Debian.<br/>
Other browser can have other render methods and another representation.
<br/>

### __Part 1 of Macro Browser__

This display part of macro browser shows your not optional URL input to get the video-resource. You can additionally set a preview to show a picture before playing.
![Graphic user interface in confluence macro browser](/images/MacroBrowser_Part1.png)
<br/>

### __Part 2 of Macro Browser__

You can use some options like autoplay, mute and loop. If you want a specific width and height, you can specify it in pixels.<br/>
![Graphic user interface in confluence macro browser](/images/MacroBrowser_Part2.png)
<br/>

### __Edit mode__

The item you will see in confluence edit mode with activated parameters. You will see this in set positions.<br/>
![View in confluence edit mode](/images/page_editmode.png)

<br/>

### __In confluence page integrated__

In your confluence page, after insert this macro, you will see this perspective.<br/>
You have the opportunity to see the video in a Picture in Picture mode as you scroll through the page.<br/>

<table style="width:100%;border-collapse: collapse;border: 2px solid black;">
  <tr style="border: 1px solid black;border-collapse: collapse;">
    <th style="border: 1px solid black;border-collapse: collapse;"><a href="/images/page_no_interaction.png"><img src="/images/page_no_interaction.png" width="300px" alt="View in confluence with no interaction"></a></th>
    <th style="border: 1px solid black;border-collapse: collapse;"><a href="/images/page_interaction.png"><img src="/images/page_interaction.png" width="300px" alt="View in confluence with interaction"></a></th>
    <th style="border: 1px solid black;border-collapse: collapse;"><a href="/images/page_pip-mode.png"><img src="/images/page_pip-mode.png" width="300px" alt="View in confluence with browser PIP mode"></a></th>
  </tr>
</table>
<br/>

## Requirements

---

* Accepted video-encoding formats (.mp4/.webm/.ogg/.ogv/.ogm/.avi/.flv).
* If no information about encoding in URL, the standard encoding format will be mp4.
* Valid confluence license to run this plugin.
* Video-resource must be available from link location.
  <br/>

## Eclipse support

---

You want to modify this plugin? You can add an eclipse support to this project with `atlas-mvn eclipse:eclipse` in the root directory. <br/>
Now open your eclipse installation and import this project as existing project with following steps: `File → Import... → "Existing Projects into Workspace"`.

## Language support

---

Supported languages in this version: English (default), German. If you want more or a specific language support, you can add
a properties file with your [country code](https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes).<br/>
__Attention__: Please make sure that you save resources in UTF-8, otherwise you can get problems with umlauts.
<br/>

## How to build and run

---

- `docker-compose up -d`
- Go to http://localhost:8090/ and complete setup

All commands below should be run in `builder` container
To build this project you need to use [SDK from Atlassian](https://developer.atlassian.com/server/framework/atlassian-sdk/downloads/).
You can build this project immediately with `atlas-mvn package`.<br/>
Upload the plugin after build example:
`atlas-install-plugin --context-path "" --server confluence -p 8090 --username admin --password admin`. <br/>
Other option: You can upload the build JAR from /target directory and install in your confluence admin area.

If you don't have a running local confluence for testing, you can use this:
Here are the SDK commands you'll use immediately:

* atlas-run   -- installs this plugin into the product and starts it on localhost
* atlas-debug -- same as atlas-run, but allows a debugger to attach at port 5005
* atlas-help  -- prints description for all commands in the SDK

Full documentation is available at:

https://developer.atlassian.com/display/DOCS/Introduction+to+the+Atlassian+Plugin+SDK

## Product support

---

Please understand that we are unable to offer support for this product.<br/>
If you have some issues or problems, you can use this e-mail: sourcecenter@1und1.de
