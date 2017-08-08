
<script type="text/javascript">
    $(document).ready(function() {
        $('#example-progress-bar').strengthMeter('progressBar', {
            container: $('#example-progress-bar-container')
        });
    });
</script>
<form class="form-horizontal">
    <div class="form-group">
        <label class="form-label col-sm-2">Password</label>
        <div class="col-sm-4">
            <input type="text" placeholder="Password ..." class="form-control" id="example-progress-bar" />
        </div>
    </div>
    <div class="form-group">
        <label class="form-label col-sm-2">Password Strength</label>
        <div class="col-sm-4" id="example-progress-bar-container">
 
        </div>
    </div>
</form>