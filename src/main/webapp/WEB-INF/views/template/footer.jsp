<%--
  Created by IntelliJ IDEA.
  User: gimgyeongmo
  Date: 3/31/24
  Time: 3:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal" tabindex="-1" id="note-message-modal">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row p-2">
                    <div class="col-2 border-end border-1 mh-600px">
                        <ul class="note-message-list list-group list-group-flush">
                            <li class="list-group-item">
                                <button id="note-message-send-form-btn" class="btn btn-primary w-100">쪽지 보내기</button>
                            </li>
                            <li id="note-message-receive-box-btn" class="list-group-item text-center">
                                <span>받은 쪽지함</span>
                            </li>
                            <li id="note-message-save-box-btn" class="list-group-item text-center">
                                <span>쪽지 보관함</span>
                            </li>
                            <li id="note-message-send-box-btn" class="list-group-item text-center">
                                <span>보낸 쪽지함</span>
                            </li>
                            <li id="note-message-delete-box-btn" class="list-group-item text-center">
                                <span>휴지통</span>
                            </li>
                        </ul>
                    </div>
                    <div id="note-message-body" class="col-10">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<footer id="footer" class="footer">
    <div class="copyright">
        &copy; Copyright <strong><span>S-Chicken</span></strong>. All Rights Reserved
    </div>
    <div class="credits">
        <!-- All the links in the footer should remain intact. -->
        <!-- You can delete the links only if you purchased the pro version. -->
        <!-- Licensing information: https://bootstrapmade.com/license/ -->
        <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ -->
<%--        Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>--%>
    </div>
</footer><!-- End Footer -->
