<html>
<head>
    <title>Code</title>

    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>

<p>
    <#if codeSnippet.views gt 0>
        <span id="views_restriction">${codeSnippet.viewsLeft} more views allowed</span><br>
    </#if>

    <#if codeSnippet.time gt 0>
        <span id="time_restriction">The code will be available for ${codeSnippet.timeLeft} seconds</span><br>
    </#if>

<pre id="code_snippet"><code>${codeSnippet.code}</code></pre>
<span id="load_date">${codeSnippet.date}</span><br>
</p>

</body>
</html>
