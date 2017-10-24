<div class="modal fade" id="uploadModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">New message</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form id="folder-form" method="POST" action="/upload?parentId=${parentId}" enctype="multipart/form-data">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="file" class="col-form-label">Select a file to upload:</label>
                        <br>
                        <input type="file" id="file" name="file" size="50"/>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" id="submit-btn" class="btn btn-primary">Upload</button>
                </div>
            </form>
        </div>
    </div>
</div>