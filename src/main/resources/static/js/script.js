<script >
    function toggleList(element) {
        var content = element.querySelector('.collapsible-content');
        content.style.display = (content.style.display === 'none' || content.style.display === '') ? 'block' : 'none';
    }
</script>