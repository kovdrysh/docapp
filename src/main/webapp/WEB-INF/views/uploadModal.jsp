<div class="modal fade" id="uploadModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">New message</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form method="POST" action="/addFolder">
                <div class="modal-body">
                    <hidden path="id" />
                    <div class="form-group">
                        <label for="folder-name" class="col-form-label">Name:</label>
                        <input id="folder-name" path="name" maxlength="30" />
                    </div>
                    <div class="form-group" hidden>
                        <label for="date" class="col-form-label">Name:</label>
                        <input path="date" id="date"/>
                    </div>
                    <div class="form-group" hidden>
                        <label for="parent-id" class="col-form-label">Parent Id:</label>
                        <input path="parentId" id="parent-id" value="${parentId}"/>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Send message</button>
                </div>
            </form>
        </div>
    </div>
</div>