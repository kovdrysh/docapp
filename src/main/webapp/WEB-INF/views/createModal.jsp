<div class="modal fade" id="createModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Create Folder</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form id="folder-form" method="POST" action="/addFolder">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="folder-name" >Name:</label>
                        <input id="folder-name" autofocus name="name" path="name" maxlength="30" />
                    </div>
                    <div class="form-group" hidden>
                        <label for="parent-id" >Parent Id:</label>
                        <input path="parentId" id="parent-id" name="parentId" value="${parentId}"/>
                    </div>
                    <div class="form-group" hidden>
                        <label for="folder-id" >Id:</label>
                        <input path="id" id="folder-id" name="id"/>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" id="submit-btn" class="btn btn-primary">Save</button>
                </div>
            </form>
        </div>
    </div>
</div>